package com.munir.crud_pessoa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import com.munir.crud_pessoa.dtos.request.TelephoneRequestDTO;
import com.munir.crud_pessoa.dtos.response.TelephoneResponseDTO;
import com.munir.crud_pessoa.entidades.Telephone;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
		uses = {TelephoneTypeMapper.class})
public interface TelephoneMapper extends BaseMapper<Telephone, TelephoneRequestDTO, TelephoneResponseDTO> {
	
	@Override
	@Mapping(source = "person.id", target = "personId")
	TelephoneResponseDTO toResponseDTO(Telephone telefone);
	
	@Override
	@Mapping(source = "personId", target = "person.id")
	Telephone toEntity(TelephoneRequestDTO requestDTO);
	
	@Override
	@Mapping(source = "personId", target = "person.id")
	void toEntityUpdate(TelephoneRequestDTO requestDTO, @MappingTarget Telephone telefone);
}