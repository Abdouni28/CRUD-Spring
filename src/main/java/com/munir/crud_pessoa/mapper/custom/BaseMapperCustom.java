package com.munir.crud_pessoa.mapper.custom;

public interface BaseMapperCustom {
	
	default <responseDTO, P> responseDTO toResponseDTO(P param) {
		return null;
	}	
	
	default <requestDTO, P> requestDTO toResquestDTO(P response) {
		return null;
	}
}
