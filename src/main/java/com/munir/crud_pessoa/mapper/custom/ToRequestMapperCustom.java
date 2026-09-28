package com.munir.crud_pessoa.mapper.custom;

public interface ToRequestMapperCustom {
	
	<requestDTO, P> requestDTO toResquestDTO(P param);
}
