package com.munir.crud_pessoa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import com.munir.crud_pessoa.dtos.request.AddressRequestDTO;
import com.munir.crud_pessoa.dtos.response.AddressResponseDTO;
import com.munir.crud_pessoa.entidades.Address;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AddressMapper extends BaseMapper<Address, AddressRequestDTO, AddressResponseDTO> {
	
	@Override
	@Mapping(source = "person.id", target = "personId")
	AddressResponseDTO toResponseDTO(Address endereco);
	
	@Override
	@Mapping(source = "personId", target = "person.id")
	Address toEntity(AddressRequestDTO dto);
	
	@Override
	@Mapping(source = "personId", target = "person.id")
	void toEntityUpdate(AddressRequestDTO dto, @MappingTarget Address endereco);
}