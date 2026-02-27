package com.munir.crud_pessoa.mapper.custom.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.javers.core.Javers;
import org.javers.core.diff.Change;
import org.javers.core.diff.Diff;
import org.javers.core.diff.changetype.InitialValueChange;
import org.javers.core.diff.changetype.ValueChange;
import org.javers.core.diff.changetype.container.ContainerElementChange;
import org.javers.core.diff.changetype.container.ListChange;
import org.javers.core.diff.changetype.container.ValueAdded;
import org.javers.core.diff.changetype.container.ValueRemoved;
import org.javers.core.metamodel.object.InstanceId;
import org.javers.shadow.Shadow;
import org.springframework.stereotype.Component;

import com.munir.crud_pessoa.dtos.response.AlteracaoCampoRevisaoDTO;
import com.munir.crud_pessoa.dtos.response.AuditoriaResponseDTO;
import com.munir.crud_pessoa.enums.TipoCommitAuditoriaENUM;
import com.munir.crud_pessoa.mapper.custom.AuditoriaMapperCustom;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuditoriaMapperCustomImpl implements AuditoriaMapperCustom {
	
	private final Javers javers;

	@Override
	@SuppressWarnings("unchecked")
	public <responseDTO, P> responseDTO toResponseDTO(P param) {

		List<Shadow<Object>> shadows = (List<Shadow<Object>>) param;
		
		if(shadows.isEmpty())
			return null;
		
		if(shadows.size() == 1) {
			
			AuditoriaResponseDTO responseDTO = inicializarResponseDTO(shadows.get(0));
			
			return (responseDTO) List.of(responseDTO);
		}

		Shadow<Object> objetoAntigo = shadows.removeFirst();
		
		AuditoriaResponseDTO responseDTO;
		List<AuditoriaResponseDTO> listResponseDTO = verificarCommitInicial(objetoAntigo);
		
		for(Shadow<Object> objetoNovo : shadows) {
			
			responseDTO = inicializarResponseDTO(objetoNovo);
			responseDTO.setAlteracoes(obterAlteracoesCampo(objetoAntigo, objetoNovo));
			
			listResponseDTO.add(responseDTO);
			
			objetoAntigo = objetoNovo;
		}
		
		limparCommitsAlteracaoSemAlteracoes(listResponseDTO);
		
		return (responseDTO) listResponseDTO;
	}
	
	private List<AuditoriaResponseDTO> verificarCommitInicial(Shadow<Object> shadow) {
		
		List<AuditoriaResponseDTO> listResponseDTO = new ArrayList<>();
		
		if(shadow.getCdoSnapshot().getType().name().equals(TipoCommitAuditoriaENUM.INSERCAO.getValorAuditoria())) 
			listResponseDTO.add(inicializarResponseDTO(shadow));
		
		return listResponseDTO;
	}
	
	private AuditoriaResponseDTO inicializarResponseDTO(Shadow<Object> shadow) {
		
		Long idRevisao = shadow.getCommitMetadata().getId().getMajorId();
		String tipoEntidade = shadow.getCdoSnapshot().getGlobalId().getTypeName();
		Long idEntidade = (Long)((InstanceId)shadow.getCdoSnapshot().getGlobalId()).getCdoId();
		String tipoOperacao = TipoCommitAuditoriaENUM.resolverParaExibicao(shadow.getCdoSnapshot().getType().name());
		LocalDateTime dataCommit = shadow.getCommitMetadata().getCommitDate();
		String enderecoIp = shadow.getCommitMetadata().getProperties().get("endereco_ip");
		
		return new AuditoriaResponseDTO(idRevisao, tipoEntidade, idEntidade, tipoOperacao, dataCommit, enderecoIp, List.of());
	}
	
	private List<AlteracaoCampoRevisaoDTO> obterAlteracoesCampo(Shadow<Object> objetoAntigo, Shadow<Object> objetoNovo) {
		
		List<AlteracaoCampoRevisaoDTO> alteracoes = new ArrayList<>();
		
		Diff diff = javers.compare(objetoAntigo.get(), objetoNovo.get());
		
		String tipoEntidadePai = objetoAntigo.getCdoSnapshot().getGlobalId().getTypeName();
		
		for (Change change : diff.getChanges()) {

			String tipoEntidade = change.getAffectedGlobalId().getTypeName();
	    	Long idEntidade = (Long)((InstanceId)change.getAffectedGlobalId()).getCdoId();
	    	
		    if (change instanceof ValueChange valueChange && !(change instanceof InitialValueChange) &&
		    	tipoEntidadePai.equals(tipoEntidade)) {
		    	
		    	String nomeCampo = valueChange.getPropertyName();
		    	String valorAntigo = String.valueOf(valueChange.getLeft());
		    	String valorNovo = String.valueOf(valueChange.getRight());

		    	alteracoes.add(new AlteracaoCampoRevisaoDTO(tipoEntidade, idEntidade, nomeCampo, valorAntigo, valorNovo));

		    } else if (change instanceof ListChange listChange) {

		        String nomeCampo = listChange.getPropertyName();

		        for (ContainerElementChange elementChange : listChange.getChanges()) {

		            if (elementChange instanceof ValueAdded valueAdded) {
		            	
		                String valorNovo = String.valueOf(valueAdded.getAddedValue());
		                
		                alteracoes.add(new AlteracaoCampoRevisaoDTO(tipoEntidade, idEntidade, nomeCampo, null, valorNovo));

		            } else if (elementChange instanceof ValueRemoved valueRemoved) {

		                String valorAntigo = String.valueOf(valueRemoved.getRemovedValue());
		                
		                alteracoes.add(new AlteracaoCampoRevisaoDTO(tipoEntidade, idEntidade, nomeCampo, valorAntigo, null));

					}
		        }
		    }
		}
		
		return alteracoes;
	}
	
	private void limparCommitsAlteracaoSemAlteracoes(List<AuditoriaResponseDTO> listResponseDTO) {
		
		listResponseDTO.removeIf(responseDTO -> responseDTO.getAlteracoes().isEmpty() && 
				responseDTO.getTipoOperacao().equals(TipoCommitAuditoriaENUM.ALTERACAO.getValorExibicao()));
	}
}
