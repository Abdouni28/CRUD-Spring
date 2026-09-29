package com.munir.crud_pessoa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import com.munir.crud_pessoa.dtos.request.TelefoneRequestDTO;
import com.munir.crud_pessoa.dtos.response.TelefoneResponseDTO;
import com.munir.crud_pessoa.entidades.Telefone;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
		uses = {TipoTelefoneMapper.class})
public interface TelefoneMapper extends BaseMapper<Telefone, TelefoneRequestDTO, TelefoneResponseDTO> {
	
	@Override
	@Mapping(source = "pessoa.id", target = "idPessoa")
	TelefoneResponseDTO toResponseDTO(Telefone telefone);
	
	@Override
	@Mapping(source = "idPessoa", target = "pessoa.id")
	Telefone toEntity(TelefoneRequestDTO requestDTO);
	
	@Override
	@Mapping(source = "idPessoa", target = "pessoa.id")
	void toEntityUpdate(TelefoneRequestDTO requestDTO, @MappingTarget Telefone telefone);
}