package com.munir.crud_pessoa.services;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.munir.crud_pessoa.dtos.request.AuditoriaRequestDTO;
import com.munir.crud_pessoa.dtos.response.AuditoriaResponseDTO;
import com.munir.crud_pessoa.mapper.custom.AuditoriaMapperCustom;
import com.munir.crud_pessoa.repositories.AuditoriaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuditoriaService {
	
	private final AuditoriaRepository repository;
	
	private final AuditoriaMapperCustom mapper;
	
	@Transactional(readOnly = true)
	@Cacheable(value = "auditoria",
	           key = "#clazz.name + '_' + #requestDTO.idEntidade() + '_' + #requestDTO.dataInicio() + '_' + #requestDTO.dataFim()")
	public <T> List<AuditoriaResponseDTO> buscarRevisoes(AuditoriaRequestDTO requestDTO, Class<T> clazz) {

		List<Object[]> revisoes = repository.buscarRevisoes(requestDTO, clazz);
		
		if(revisoes.isEmpty()) 
			return null;
		
		List<AuditoriaResponseDTO> responseDTO = mapper.toResponseDTO(revisoes);
		
		return responseDTO;
	}
}
