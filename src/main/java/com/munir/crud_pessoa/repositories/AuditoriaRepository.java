package com.munir.crud_pessoa.repositories;

import org.springframework.data.repository.Repository;

import com.munir.crud_pessoa.entidades.Auditoria;
import com.munir.crud_pessoa.repositories.custom.AuditoriaRepositoryCustom;

public interface AuditoriaRepository extends Repository<Auditoria, Integer>, AuditoriaRepositoryCustom {}
