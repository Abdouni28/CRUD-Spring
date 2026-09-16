package com.munir.crud_pessoa.dtos.request;

import java.io.Serializable;
import java.util.Set;

import org.springframework.data.domain.Pageable;

public record ValidacaoPageableSortRequestDTO(Pageable pageable, Set<String> sortProperties) implements Serializable {

}
