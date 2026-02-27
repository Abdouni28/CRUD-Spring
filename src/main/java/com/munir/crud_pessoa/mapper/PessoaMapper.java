package com.munir.crud_pessoa.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import com.munir.crud_pessoa.dtos.request.EnderecoRequestDTO;
import com.munir.crud_pessoa.dtos.request.PessoaRequestDTO;
import com.munir.crud_pessoa.dtos.request.TelefoneRequestDTO;
import com.munir.crud_pessoa.dtos.response.PessoaResponseDTO;
import com.munir.crud_pessoa.entidades.Endereco;
import com.munir.crud_pessoa.entidades.Pessoa;
import com.munir.crud_pessoa.entidades.Telefone;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
		uses = {EnderecoMapper.class, TelefoneMapper.class})
public abstract class PessoaMapper implements BaseMapper<Pessoa, PessoaRequestDTO, PessoaResponseDTO> {

	@Autowired
	EnderecoMapper enderecoMapper;
	
	@Autowired
	TelefoneMapper telefoneMapper;
	
	@Override
    @Mapping(target = "enderecos", ignore = true)
    @Mapping(target = "telefones", ignore = true)
	public abstract void toEntityUpdate(PessoaRequestDTO dto, @MappingTarget Pessoa entity);	

	public void sincronizarEnderecos(Pessoa pessoa, List<EnderecoRequestDTO> enderecosDTO) {
		
		List<Endereco> enderecosAtuais = pessoa.getEnderecos();
		
	    enderecosAtuais.removeIf(endereco ->
	    	enderecosDTO.stream().noneMatch(dto ->
	            dto.id() != null && dto.id().equals(endereco.getId())
	        )
	    );

	    for (EnderecoRequestDTO dto : enderecosDTO) {
	    	
	    	//endereço novo
	        if (dto.id() == null) {

	            Endereco novo = enderecoMapper.toEntity(dto);
	            novo.setPessoa(pessoa);
	            
	            enderecosAtuais.add(novo);
	            
	        } else {
	        	
	            //endereço já existente
	            Endereco existente = enderecosAtuais.stream()
	                .filter(e -> e.getId().equals(dto.id()))
	                .findFirst()
	                .orElseThrow(() -> new RuntimeException(String.format("Endereco %s não encontrado", dto.id())));

	            enderecoMapper.toEntityUpdate(dto, existente);
	            
	            existente.setPessoa(pessoa);
	        }
	    }
	}

	public void sincronizarTelefones(Pessoa pessoa, List<TelefoneRequestDTO> telefonesDTO) {
		
		List<Telefone> telefonesAtuais = pessoa.getTelefones();
		
		telefonesAtuais.removeIf(endereco ->
	    	telefonesDTO.stream().noneMatch(dto ->
	            dto.id() != null && dto.id().equals(endereco.getId())
	        )
	    );

	    for (TelefoneRequestDTO dto : telefonesDTO) {
	    	
	    	//endereço novo
	        if (dto.id() == null) {

	            Telefone novo = telefoneMapper.toEntity(dto);
	            novo.setPessoa(pessoa);
	            
	            telefonesAtuais.add(novo);
	            
	        } else {
	        	
	            //endereço já existente
	            Telefone existente = telefonesAtuais.stream()
	                .filter(e -> e.getId().equals(dto.id()))
	                .findFirst()
	                .orElseThrow(() -> new RuntimeException(String.format("Telefone %s não encontrado", dto.id())));

	            telefoneMapper.toEntityUpdate(dto, existente);
	            
	            existente.setPessoa(pessoa);
	        }
	    }
	}
}
