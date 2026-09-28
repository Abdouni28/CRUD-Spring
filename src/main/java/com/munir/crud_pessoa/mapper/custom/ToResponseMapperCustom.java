package com.munir.crud_pessoa.mapper.custom;

public interface ToResponseMapperCustom {
	
	<responseDTO, P> responseDTO toResponseDTO(P param);
}
