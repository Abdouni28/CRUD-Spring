package com.munir.crud_pessoa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.munir.crud_pessoa.entidades.Telefone;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

}
