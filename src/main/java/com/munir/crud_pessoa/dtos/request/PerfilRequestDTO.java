package com.munir.crud_pessoa.dtos.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PerfilRequestDTO(Long id, @JsonProperty("nome") String nome) implements Serializable {
}