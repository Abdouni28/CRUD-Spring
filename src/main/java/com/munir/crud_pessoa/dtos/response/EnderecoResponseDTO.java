package com.munir.crud_pessoa.dtos.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EnderecoResponseDTO(Long id, String logradouro, String nomeLogradouro, String numero, String bairro,
								  String cidade, String estado, String cep,
								  @JsonProperty("id_pessoa") Long idPessoa) implements Serializable {
}