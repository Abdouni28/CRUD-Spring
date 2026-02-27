package com.munir.crud_pessoa.services;

import java.util.List;

import org.javers.shadow.Shadow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.munir.crud_pessoa.dtos.request.AuditoriaRequestDTO;
import com.munir.crud_pessoa.dtos.response.AuditoriaResponseDTO;
import com.munir.crud_pessoa.mapper.custom.AuditoriaMapperCustom;
import com.munir.crud_pessoa.repositories.custom.AuditoriaRepositoryCustom;

@Service
public class AuditoriaService {
	
	@Autowired
	AuditoriaRepositoryCustom repository;
	
	@Autowired
	AuditoriaMapperCustom mapper;
	
	@Cacheable(value = "auditoria",
	           key = "#clazz.name + '_' + #requestDTO.idEntidade() + '_' + #requestDTO.dataInicio() + '_' + #requestDTO.dataFim()")
	public List<AuditoriaResponseDTO> buscarRevisoes(AuditoriaRequestDTO requestDTO, Class<?> clazz) {
		
		List<Shadow<Object>> shadows = repository.buscarRevisoes(requestDTO, clazz);

		return mapper.toResponseDTO(shadows);
	}
}
