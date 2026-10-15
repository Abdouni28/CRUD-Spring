package com.munir.crud_pessoa.dtos.request;

import java.io.Serializable;

public record ProfileRequestDTO(Long id, String name) implements Serializable {}