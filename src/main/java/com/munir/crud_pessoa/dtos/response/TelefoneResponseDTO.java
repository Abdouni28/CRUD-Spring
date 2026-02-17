package com.munir.crud_pessoa.dtos.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.munir.crud_pessoa.dtos.request.TipoTelefoneRequestDTO;

public record TelefoneResponseDTO(Long id, String numero,
		 						  @JsonProperty("tipo_telefone") TipoTelefoneRequestDTO tipoTelefone) implements Serializable {
}