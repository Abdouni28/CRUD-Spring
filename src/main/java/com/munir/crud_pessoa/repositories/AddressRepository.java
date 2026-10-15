package com.munir.crud_pessoa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.munir.crud_pessoa.entidades.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {}