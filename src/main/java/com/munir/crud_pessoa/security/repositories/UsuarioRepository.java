package com.munir.crud_pessoa.security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.munir.crud_pessoa.security.entidades.User;

public interface UsuarioRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsernameAndActiveTrue(String username);
}