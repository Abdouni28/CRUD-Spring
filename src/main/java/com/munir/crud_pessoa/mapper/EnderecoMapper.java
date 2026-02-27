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
    @Mapping(target = "pessoa", ignore = true)
	void toEntityUpdate(EnderecoRequestDTO dto, @MappingTarget Endereco entity);
}