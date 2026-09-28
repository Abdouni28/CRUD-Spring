package com.munir.crud_pessoa.mapper.custom.impl;

import java.lang.reflect.Field;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.envers.RevisionType;
import org.springframework.stereotype.Component;

import com.munir.crud_pessoa.dtos.response.AlteracaoCampoRevisaoDTO;
import com.munir.crud_pessoa.dtos.response.AuditoriaResponseDTO;
import com.munir.crud_pessoa.entidades.Auditoria;
import com.munir.crud_pessoa.enums.TipoOperacaoAuditoriaENUM;
import com.munir.crud_pessoa.mapper.custom.AuditoriaMapperCustom;
import com.munir.crud_pessoa.utils.ReflexaoUtils;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuditoriaMapperCustomImpl implements AuditoriaMapperCustom {
	
	@Override
	@SuppressWarnings("unchecked")
	public <responseDTO, P> responseDTO toResponseDTO(P param) {

		List<Object[]> revisoes = (List<Object[]>) param;
		
		if(revisoes.size() == 1) {
			
			RevisionType tipoOperacao = (RevisionType) revisoes.get(0)[2];
			
			if(tipoOperacao == RevisionType.MOD) {
				
				return (responseDTO) List.of();
				
			} else {
				
				AuditoriaResponseDTO responseDTO = mapearRevisao(null, revisoes.get(0));
				
				return (responseDTO) List.of(responseDTO);
			}
		}

		AuditoriaResponseDTO primeiraAuditoriaResponseDTO = mapearRevisao(null, revisoes.get(0));
		
		List<AuditoriaResponseDTO> responseDTO = new ArrayList<>();		
		responseDTO.add(primeiraAuditoriaResponseDTO);
		
		Object[] revisaoAnterior = revisoes.remove(0);
		
		for(Object[] revisao : revisoes) {
			
			AuditoriaResponseDTO auditoriaResponseDTO = mapearRevisao(revisaoAnterior, revisao);
			
			responseDTO.add(auditoriaResponseDTO);
			
			revisaoAnterior = revisao;
		}
		
		return (responseDTO) responseDTO;
	}
	
	private AuditoriaResponseDTO mapearRevisao(Object[] revisaoAnterior, Object[] revisaoAtual) {
		
		Auditoria auditoria = (Auditoria) revisaoAtual[1];
		RevisionType tipoOperacaoOriginal = (RevisionType) revisaoAtual[2];
		
		Long idRevisao = Long.valueOf(auditoria.getId());
		LocalDateTime dataRevisao = Instant.ofEpochMilli(auditoria.getTimestamp()).atZone(ZoneId.systemDefault()).toLocalDateTime();
		String tipoOperacao = TipoOperacaoAuditoriaENUM.fromRevisionType(tipoOperacaoOriginal).getDescricao();
		String enderecoIp = auditoria.getEnderecoIp();
		String autor = auditoria.getAutor();
		
		List<AlteracaoCampoRevisaoDTO> alteracoes = mapearAlteracoesRevisao(revisaoAnterior, revisaoAtual);
		
		AuditoriaResponseDTO responseDTO = new AuditoriaResponseDTO(idRevisao, dataRevisao, tipoOperacao, enderecoIp, autor, alteracoes);
		
		return responseDTO;
	}
	
	private List<AlteracaoCampoRevisaoDTO> mapearAlteracoesRevisao(Object[] revisaoAnterior, Object[] revisaoAtual) {

		List<AlteracaoCampoRevisaoDTO> alteracoes = new ArrayList<>();
		
		if(revisaoAnterior != null && revisaoAtual != null) {
			
			Object entidadeAnterior = revisaoAnterior[0];
			Object entidadeAtual = revisaoAtual[0];
			
			Class<?> clazz = entidadeAtual.getClass();
			
			for(var field : clazz.getDeclaredFields()) {
				
				field.setAccessible(true);
				
				if(ReflexaoUtils.isNativeField(field)) {
					
					try {
						
						Object valorAntigo = field.get(entidadeAnterior);
						Object valorNovo = field.get(entidadeAtual);
						
						if((valorAntigo == null && valorNovo != null) || (valorAntigo != null && !valorAntigo.equals(valorNovo))) {
							AlteracaoCampoRevisaoDTO alteracao = new AlteracaoCampoRevisaoDTO(field.getName(), valorAntigo.toString(), valorNovo.toString());
							alteracoes.add(alteracao);
						}
						
					} catch (IllegalAccessException e) {
						
						e.printStackTrace();
					}
				}				
			}
		}
		
		return alteracoes;
	}
}