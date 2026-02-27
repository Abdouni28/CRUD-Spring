package com.munir.crud_pessoa.mapper;

import java.util.List;

import org.mapstruct.MappingTarget;

public interface BaseMapper<entidade, requestDTO, responseDTO> {

	responseDTO toResponseDTO(entidade entity);
	entidade toEntity(requestDTO dto);
	
	List<responseDTO> toResponseDTOList(List<entidade> entityList);
	List<entidade> toEntityList(List<requestDTO> dtoList);
	
	void toEntityUpdate(requestDTO dto, @MappingTarget entidade entity);
}
