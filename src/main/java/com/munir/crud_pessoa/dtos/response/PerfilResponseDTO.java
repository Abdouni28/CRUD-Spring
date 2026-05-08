package com.munir.crud_pessoa.dtos.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PerfilResponseDTO(Long id, @JsonProperty("nome") String nome) implements Serializable {
}