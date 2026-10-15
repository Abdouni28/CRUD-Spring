package com.munir.crud_pessoa.repositories.custom;

import java.util.List;

import com.munir.crud_pessoa.dtos.request.AuditRequestDTO;

public interface AuditRepositoryCustom {
	
	<T> List<Object[]> findRevisions(AuditRequestDTO requestDTO, Class<T> clazz);
}