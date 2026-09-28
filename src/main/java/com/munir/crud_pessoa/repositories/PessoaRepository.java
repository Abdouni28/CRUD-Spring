package com.munir.crud_pessoa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.munir.crud_pessoa.entidades.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>, JpaSpecificationExecutor<Pessoa> {

	Optional<Pessoa> findByCpfAndAtivaTrue(String cpf);
}
