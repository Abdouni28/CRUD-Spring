package com.munir.crud_pessoa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.munir.crud_pessoa.dtos.request.EnderecoRequestDTO;
import com.munir.crud_pessoa.dtos.response.EnderecoResponseDTO;
import com.munir.crud_pessoa.entidades.Endereco;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EnderecoMapper extends BaseMapper<Endereco, EnderecoRequestDTO, EnderecoResponseDTO> {
}