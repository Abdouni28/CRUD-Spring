package com.munir.crud_pessoa.repositories.custom.impl;

import java.util.List;

import org.javers.core.Javers;
import org.javers.core.metamodel.object.InstanceId;
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
		
		JqlQuery query = QueryBuilder.byClass(clazz)
									 .to(requestDTO.dataFim())
	            					 .withScopeCommitDeep()
            					 	 .build();
		
		List<Shadow<Object>> shadows = javers.findShadows(query).reversed();
		shadows.removeIf(shadow -> (Long)((InstanceId)shadow.getCdoSnapshot().getGlobalId()).getCdoId() != requestDTO.idEntidade());
		
		return shadows;
	}
}