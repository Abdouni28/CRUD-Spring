package com.munir.crud_pessoa.mapper;

import java.util.List;

public interface BaseMapper<entidade, requestDTO, responseDTO> {

	responseDTO toResponseDTO(entidade entity);
	entidade toEntity(requestDTO dto);
	
	List<responseDTO> toResponseDTOList(List<entidade> entityList);
	List<entidade> toEntityList(List<requestDTO> dtoList);
}
