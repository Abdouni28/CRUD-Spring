package com.munir.crud_pessoa.repositories.custom;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.munir.crud_pessoa.dtos.request.filtros_busca.PersonSearchFiltersRequestDTO;
import com.munir.crud_pessoa.entidades.Person;

public interface PersonRepositoryCustom {
	
	List<Person> find(PersonSearchFiltersRequestDTO request, Pageable pageable);
}