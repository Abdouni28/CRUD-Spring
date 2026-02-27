package com.munir.crud_pessoa.repositories.custom;

import java.util.List;

import org.javers.shadow.Shadow;

import com.munir.crud_pessoa.dtos.request.AuditoriaRequestDTO;

public interface AuditoriaRepositoryCustom {
	
	List<Shadow<Object>> buscarRevisoes(AuditoriaRequestDTO requestDTO, Class<?> clazz);

}
