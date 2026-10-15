package com.munir.crud_pessoa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.munir.crud_pessoa.dtos.request.ProfileRequestDTO;
import com.munir.crud_pessoa.dtos.response.ProfileResponseDTO;
import com.munir.crud_pessoa.security.entidades.Profile;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProfileMapper extends BaseMapper<Profile, ProfileRequestDTO, ProfileResponseDTO> {}