package com.munir.crud_pessoa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import com.munir.crud_pessoa.dtos.request.EnderecoRequestDTO;
import com.munir.crud_pessoa.dtos.response.EnderecoResponseDTO;
import com.munir.crud_pessoa.entidades.Endereco;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EnderecoMapper extends BaseMapper<Endereco, EnderecoRequestDTO, EnderecoResponseDTO> {
	
	@Override
	@Mapping(source = "pessoa.id", target = "idPessoa")
	EnderecoResponseDTO toResponseDTO(Endereco endereco);
	
	@Override
	@Mapping(source = "idPessoa", target = "pessoa.id")
	Endereco toEntity(EnderecoRequestDTO dto);
	
	@Override
	@Mapping(source = "idPessoa", target = "pessoa.id")
	void toEntityUpdate(EnderecoRequestDTO dto, @MappingTarget Endereco endereco);
}