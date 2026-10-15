package com.munir.crud_pessoa.repositories;

import org.springframework.data.repository.Repository;

import com.munir.crud_pessoa.entidades.Audit;
import com.munir.crud_pessoa.repositories.custom.AuditRepositoryCustom;

public interface AuditRepository extends Repository<Audit, Integer>, AuditRepositoryCustom {}
