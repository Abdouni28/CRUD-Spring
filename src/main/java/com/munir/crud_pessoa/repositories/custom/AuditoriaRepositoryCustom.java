package com.munir.crud_pessoa.repositories.custom;

import java.util.List;

import com.munir.crud_pessoa.dtos.request.AuditoriaRequestDTO;

public interface AuditoriaRepositoryCustom {
	
	<T> List<Object[]> buscarRevisoes(AuditoriaRequestDTO requestDTO, Class<T> clazz);
}
