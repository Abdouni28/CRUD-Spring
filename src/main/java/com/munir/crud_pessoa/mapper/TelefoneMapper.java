package com.munir.crud_pessoa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.munir.crud_pessoa.dtos.request.TelefoneRequestDTO;
import com.munir.crud_pessoa.dtos.response.TelefoneResponseDTO;
import com.munir.crud_pessoa.entidades.Telefone;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
		uses = {TipoTelefoneMapper.class})
public interface TelefoneMapper extends BaseMapper<Telefone, TelefoneRequestDTO, TelefoneResponseDTO> {
}