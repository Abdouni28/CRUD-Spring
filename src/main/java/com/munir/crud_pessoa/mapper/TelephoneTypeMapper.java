package com.munir.crud_pessoa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.munir.crud_pessoa.dtos.request.TelephoneTypeRequestDTO;
import com.munir.crud_pessoa.dtos.response.TelephoneTypeResponseDTO;
import com.munir.crud_pessoa.entidades.TelephoneType;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TelephoneTypeMapper extends BaseMapper<TelephoneType, TelephoneTypeRequestDTO, TelephoneTypeResponseDTO> {}