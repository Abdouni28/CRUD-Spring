package com.munir.crud_pessoa.services;

import org.springframework.stereotype.Service;

import com.munir.crud_pessoa.entidades.EmailDeliverLog;
import com.munir.crud_pessoa.repositories.EmailDeliverLogRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailDeliverLogService {
	
	private final EmailDeliverLogRepository repository;	 
	
	public void save(EmailDeliverLog emailDeliverLog){
			
		repository.save(emailDeliverLog);
	}
}