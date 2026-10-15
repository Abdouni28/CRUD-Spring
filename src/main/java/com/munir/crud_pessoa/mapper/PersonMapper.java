package com.munir.crud_pessoa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.munir.crud_pessoa.dtos.request.PersonRequestDTO;
import com.munir.crud_pessoa.dtos.response.PersonResponseDTO;
import com.munir.crud_pessoa.entidades.Person;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
		uses = {AddressMapper.class, TelephoneMapper.class, UserMapper.class})
public interface PersonMapper extends BaseMapper<Person, PersonRequestDTO, PersonResponseDTO> {}