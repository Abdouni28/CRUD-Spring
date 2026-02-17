package com.munir.crud_pessoa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.munir.crud_pessoa.dtos.request.TipoTelefoneRequestDTO;
import com.munir.crud_pessoa.dtos.response.TipoTelefoneResponseDTO;
import com.munir.crud_pessoa.entidades.TipoTelefone;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TipoTelefoneMapper extends BaseMapper<TipoTelefone, TipoTelefoneRequestDTO, TipoTelefoneResponseDTO> {
}