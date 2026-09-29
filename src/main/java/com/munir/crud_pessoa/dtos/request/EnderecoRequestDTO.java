package com.munir.crud_pessoa.dtos.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EnderecoRequestDTO(Long id, String logradouro, String nomeLogradouro, String numero, String bairro,
		  						 String cidade, String estado, String cep,
		  						 @JsonProperty("id_pessoa") Long idPessoa) implements Serializable {
}