package com.munir.crud_pessoa.repositories.custom.impl;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Repository
public class BaseRepositoryCustomImpl<T> {
	
	protected static final String LIKE = "%";
	
	@PersistenceContext
    protected EntityManager entityManager;
    
	protected CriteriaBuilder cb;

    protected CriteriaQuery<T> query;

    protected Root<T> root;
    
    protected void initializeCriteria(Class<T> clazz) {
    	
		cb = entityManager.getCriteriaBuilder();
		query = cb.createQuery(clazz);
		root = query.from(clazz);
    	
    }
    
    protected void applyPagination(Query query, Pageable pageable) {
    	
		query.setFirstResult(Math.toIntExact(pageable.getOffset()));
		query.setMaxResults(pageable.getPageSize());
	}
}