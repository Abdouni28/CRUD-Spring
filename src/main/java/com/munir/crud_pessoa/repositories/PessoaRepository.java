package com.munir.crud_pessoa.repositories;

import java.util.Optional;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.munir.crud_pessoa.entidades.Pessoa;

@Repository
@JaversSpringDataAuditable
public interface PessoaRepository extends JpaRepository<Pessoa, Long>, JpaSpecificationExecutor<Pessoa> {

	Optional<Pessoa> findByCpfAndAtivaTrue(String cpf);
}
