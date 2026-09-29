package com.munir.crud_pessoa.dtos.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TelefoneRequestDTO(Long id, String numero, @JsonProperty("tipo_telefone") TipoTelefoneRequestDTO tipoTelefone,
								 @JsonProperty("id_pessoa") Long idPessoa) implements Serializable {
}