package com.munir.crud_pessoa.repositories.custom.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.munir.crud_pessoa.dtos.request.filtros_busca.PersonSearchFiltersRequestDTO;
import com.munir.crud_pessoa.entidades.Address;
import com.munir.crud_pessoa.entidades.Person;
import com.munir.crud_pessoa.entidades.Telephone;
import com.munir.crud_pessoa.entidades.TelephoneType;
import com.munir.crud_pessoa.repositories.custom.PersonRepositoryCustom;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;

@Repository
public class PersonRepositoryCustomImpl extends BaseRepositoryCustomImpl<Person> implements PersonRepositoryCustom {
	
	@Override
	public List<Person> find(PersonSearchFiltersRequestDTO request, Pageable pageable) {

		initializeCriteria(Person.class);

        Join<Person, Address> address = root.join("addresses", JoinType.INNER);
        Join<Person, Telephone> telephone = root.join("telephones", JoinType.INNER);
        Join<Telephone, TelephoneType> telephoneType = telephone.join("telephoneType", JoinType.INNER);

        List<Predicate> predicates = new ArrayList<>();
        
        buildPersonPredicates(request, predicates);
        buildAddressPredicates(request, address, predicates);
        buildTelephonePredicates(request, telephone, predicates);
        buildTelephoneTypePredicates(request, telephoneType, predicates);
        
        query.select(root).where(predicates.toArray(new Predicate[0])).distinct(true);
        
        TypedQuery<Person> typedQuery = entityManager.createQuery(query);

        applyPagination(typedQuery, pageable);

        return typedQuery.getResultList();
	}
	
	private void buildPersonPredicates(PersonSearchFiltersRequestDTO request, List<Predicate> predicates) {
		
		if(request.personId() != null)
			predicates.add(cb.equal(root.get("id"), request.personId()));
		
		if(request.name() != null)
			predicates.add(cb.like(cb.lower(root.get("name")), LIKE + request.name().toLowerCase() + LIKE));
		
		if(request.cpf() != null)
			predicates.add(cb.like(cb.lower(root.get("cpf")), LIKE + request.cpf() + LIKE));
		
		if(request.email() != null)
			predicates.add(cb.like(cb.lower(root.get("email")), LIKE + request.email().toLowerCase() + LIKE));
		
		if(request.fromBirthDate() != null)
			predicates.add(cb.greaterThanOrEqualTo(root.get("birthDate"), request.fromBirthDate()));
		
		if(request.toBirthDate() != null)
			predicates.add(cb.lessThanOrEqualTo(root.get("birthDate"), request.toBirthDate()));
		
		if(request.active() != null)
			predicates.add(cb.equal(root.get("active"), request.active()));				
	}
	
	private void buildAddressPredicates(PersonSearchFiltersRequestDTO request, Join<Person, Address> address, List<Predicate> predicates) {
		
		if(request.addressId() != null)
			predicates.add(cb.equal(address.get("id"), request.addressId()));
		
		if(request.address() != null)
			predicates.add(cb.like(cb.lower(address.get("address")), LIKE + request.address().toLowerCase() + LIKE));
		
		if(request.addressType() != null)
			predicates.add(cb.like(cb.lower(address.get("type")), LIKE + request.addressType().toLowerCase() + LIKE));
		
		if(request.addressNumber() != null)
			predicates.add(cb.like(cb.lower(address.get("number")), LIKE + request.addressNumber().toLowerCase() + LIKE));
		
		if(request.neighborhood() != null)
			predicates.add(cb.like(cb.lower(address.get("neighborhood")), LIKE + request.neighborhood().toLowerCase() + LIKE));
		
		if(request.city() != null)
			predicates.add(cb.like(cb.lower(address.get("city")), LIKE + request.city().toLowerCase() + LIKE));
		
		if(request.state() != null)
			predicates.add(cb.like(cb.lower(address.get("state")), LIKE + request.state().toLowerCase() + LIKE));
		
		if(request.cep() != null)
			predicates.add(cb.like(cb.lower(address.get("cep")), LIKE + request.cep().toLowerCase() + LIKE));
	}
	
	private void buildTelephonePredicates(PersonSearchFiltersRequestDTO request, Join<Person, Telephone> telephone, List<Predicate> predicates) {
		
		if(request.telephoneId() != null)
			predicates.add(cb.equal(telephone.get("id"), request.telephoneId()));
		
		if(request.telephoneNumber() != null)
			predicates.add(cb.like(cb.lower(telephone.get("number")), LIKE + request.telephoneNumber().toLowerCase() + LIKE));
	}
	
	
	private void buildTelephoneTypePredicates(PersonSearchFiltersRequestDTO request, Join<Telephone, TelephoneType> telephoneType, List<Predicate> predicates) {
		
		if(request.telephoneTypeId() != null)
			predicates.add(cb.equal(telephoneType.get("id"), request.telephoneTypeId()));
		
		if(request.telephoneTypeDescription() != null)
			predicates.add(cb.like(cb.lower(telephoneType.get("description")), LIKE + request.telephoneTypeDescription().toLowerCase() + LIKE));
	}
}