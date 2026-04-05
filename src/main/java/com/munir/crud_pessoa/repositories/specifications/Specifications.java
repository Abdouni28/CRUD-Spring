package com.munir.crud_pessoa.repositories.specifications;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;

public class Specifications {

	protected static final String LIKE = "%";
	
	protected static <T> Specification<T> string(String campo, String valor) {
		
        return (root, query, cb) -> valor == null ? null : cb.like(root.get(campo), LIKE + valor + LIKE);
    }
	
	protected static <T> Specification<T> dataDe(String campo, LocalDate data) {
		
        return (root, query, cb) -> data == null ? null : cb.greaterThanOrEqualTo(root.<LocalDate>get(campo), data);
    }
	
	protected static <T> Specification<T> dataAte(String campo, LocalDate data) {
		
        return (root, query, cb) -> data == null ? null : cb.lessThanOrEqualTo(root.<LocalDate>get(campo), data);
    }
	
	protected static <T1, T2> Join<T1, T2> montarJoin(Root<T1> root, String campo) {
		
		return root.join(campo);
		
	}
	
	protected static <T1, T2> Specification<T1> joinLong(String campoJoin, String campo, Long valor) {
		
        return (root, query, cb) -> {
        	
        	if(valor == null) return null;
        	
        	Join<T1, T2> join = montarJoin(root, campoJoin);
        	
        	return cb.equal(join.get(campo), valor);
        };
    }
	
	protected static <T1, T2> Specification<T1> joinString(String campoJoin, String campo, String valor) {
		
        return (root, query, cb) -> {
        	
        	if(valor == null) return null;
        	
        	Join<T1, T2> join = montarJoin(root, campoJoin);
        	
        	return cb.like(join.get(campo), LIKE + valor + LIKE);
        };
    }
}
