package com.munir.crud_pessoa.services;

import org.springframework.stereotype.Service;

import com.munir.crud_pessoa.entidades.LogEnvioEmail;
import com.munir.crud_pessoa.repositories.LogEnvioEmailRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogEnvioEmailService {
	
	private final LogEnvioEmailRepository logEnvioEmailRepository;	 
	
	public void save(LogEnvioEmail logEnvioEmail){
			
		logEnvioEmailRepository.save(logEnvioEmail);
	}
}