package com.munir.crud_pessoa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.munir.crud_pessoa.dtos.request.PerfilRequestDTO;
import com.munir.crud_pessoa.dtos.response.PerfilResponseDTO;
import com.munir.crud_pessoa.security.entidades.Perfil;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PerfilMapper extends BaseMapper<Perfil, PerfilRequestDTO, PerfilResponseDTO> {
}