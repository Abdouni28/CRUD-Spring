package com.munir.crud_pessoa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.munir.crud_pessoa.entidades.Person;
import com.munir.crud_pessoa.repositories.custom.PersonRepositoryCustom;

public interface PersonRepository extends JpaRepository<Person, Long>, PersonRepositoryCustom {

	Optional<Person> findByCpfAndActiveTrue(String cpf);
}