package com.munir.crud_pessoa.security.repositories;

import java.util.Optional;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.munir.crud_pessoa.security.entidades.Usuario;

@Repository
@JaversSpringDataAuditable
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByNomeUsuarioAndAtivoTrue(String nomeUsuario);
}
