package com.munir.crud_pessoa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.munir.crud_pessoa.entidades.LogEnvioEmail;

public interface LogEnvioEmailRepository extends JpaRepository<LogEnvioEmail, Long> {
	
}
