package com.munir.crud_pessoa.dtos.request;

import java.io.Serializable;

import com.munir.crud_pessoa.enums.DocumentTypeENUM;

public record DocumentValidationRequestDTO(String document, DocumentTypeENUM documentType) implements Serializable {}