package com.munir.crud_pessoa.services;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.munir.crud_pessoa.dtos.request.AuditRequestDTO;
import com.munir.crud_pessoa.dtos.response.AuditResponseDTO;
import com.munir.crud_pessoa.mapper.custom.AuditMapperCustom;
import com.munir.crud_pessoa.repositories.AuditRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuditService {
	
	private final AuditRepository repository;
	
	private final AuditMapperCustom mapper;
	
	@Transactional(readOnly = true)
	@Cacheable(value = "audit",
	           key = "#clazz.name + '_' + #request.entityId() + '_' + #request.fromDate() + '_' + #request.toDate()")
	public <T> List<AuditResponseDTO> getAudit(AuditRequestDTO request, Class<T> clazz) {

		List<Object[]> revisions = repository.findRevisions(request, clazz);
		
		if(revisions.isEmpty()) 
			return null;
		
		List<AuditResponseDTO> response = mapper.toResponseDTO(revisions);
		
		return response;
	}
}
