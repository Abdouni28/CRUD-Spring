package com.munir.crud_pessoa.repositories.custom.impl;

import java.util.List;

import org.javers.core.Javers;
import org.javers.repository.jql.JqlQuery;
import org.javers.repository.jql.QueryBuilder;
import org.javers.shadow.Shadow;
import org.springframework.stereotype.Repository;

import com.munir.crud_pessoa.dtos.request.AuditoriaRequestDTO;
import com.munir.crud_pessoa.repositories.custom.AuditoriaRepositoryCustom;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AuditoriaRepositoryCustomImpl implements AuditoriaRepositoryCustom {
	
	private final Javers javers;
	
	@Override
	public List<Shadow<Object>> buscarRevisoes(AuditoriaRequestDTO requestDTO, Class<?> clazz) {
		
		JqlQuery query = QueryBuilder.byInstanceId(requestDTO.idEntidade(), clazz)
	            					 .from(requestDTO.dataInicio())	
									 .to(requestDTO.dataFim())
	            					 .withScopeCommitDeep()
            					 	 .build();
		
		return javers.findShadows(query).reversed();
	}
}