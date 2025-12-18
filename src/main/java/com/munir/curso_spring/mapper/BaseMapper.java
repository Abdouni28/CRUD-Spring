package com.munir.curso_spring.mapper;

import java.util.List;

public interface BaseMapper<E, D> {

	D toDTO(E entity);
	E toEntity(D dto);
	
	List<D> toDTOList(List<E> entityList);
	List<E> toEntityList(List<D> dtoList);
}
