package com.munir.crud_pessoa.repositories.custom.impl;

import java.util.List;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.stereotype.Repository;

import com.munir.crud_pessoa.dtos.request.AuditoriaRequestDTO;
import com.munir.crud_pessoa.repositories.custom.AuditoriaRepositoryCustom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class AuditoriaRepositoryCustomImpl implements AuditoriaRepositoryCustom {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@SuppressWarnings("unchecked")
	public <T> List<Object[]> buscarRevisoes(AuditoriaRequestDTO requestDTO, Class<T> clazz) {	    

        AuditReader auditReader = AuditReaderFactory.get(entityManager);

        AuditQuery query = auditReader.createQuery().forRevisionsOfEntity(clazz, false, true);
        query.add(AuditEntity.id().eq(requestDTO.idEntidade()));

        if (requestDTO.dataInicio() != null)
            query.add(AuditEntity.revisionProperty("timestamp").ge(requestDTO.dataInicio()));
        
        if (requestDTO.dataFim() != null)
            query.add(AuditEntity.revisionProperty("timestamp").le(requestDTO.dataFim()));

        List<Object[]> revisoes = query.getResultList();
        
        return revisoes;
	}
}