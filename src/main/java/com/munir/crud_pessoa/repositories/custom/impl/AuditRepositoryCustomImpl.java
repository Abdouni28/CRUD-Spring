package com.munir.crud_pessoa.repositories.custom.impl;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.stereotype.Repository;

import com.munir.crud_pessoa.dtos.request.AuditRequestDTO;
import com.munir.crud_pessoa.entidades.Audit;
import com.munir.crud_pessoa.repositories.custom.AuditRepositoryCustom;

@Repository
public class AuditRepositoryCustomImpl extends BaseRepositoryCustomImpl<Audit> implements AuditRepositoryCustom {
	
	private static final String TIMESTAMP = "timestamp";
	
	@Override
	@SuppressWarnings("unchecked")
	public <T> List<Object[]> findRevisions(AuditRequestDTO request, Class<T> clazz) {	    

        AuditReader auditReader = AuditReaderFactory.get(entityManager);

        AuditQuery query = auditReader.createQuery().forRevisionsOfEntity(clazz, false, true);
        query.add(AuditEntity.id().eq(request.entityId()));

        if (request.fromDate() != null) {
        	
            Instant fromInstant = request.fromDate().atZone(ZoneId.of("America/Sao_Paulo")).toInstant();            

            query.add(AuditEntity.revisionProperty(TIMESTAMP).ge(fromInstant.toEpochMilli()));
        }
        
        if (request.toDate() != null) {
			
        	Instant toInstant = request.toDate().atZone(ZoneId.of("America/Sao_Paulo")).toInstant();            

			query.add(AuditEntity.revisionProperty(TIMESTAMP).le(toInstant.toEpochMilli()));
        }

        List<Object[]> revisions = query.getResultList();
        
        return revisions;
	}
}