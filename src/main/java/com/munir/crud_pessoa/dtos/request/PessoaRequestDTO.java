package com.munir.crud_pessoa.dtos.request;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PessoaRequestDTO(Long id, String nome, String cpf, String email,
							   @JsonProperty("data_nascimento") LocalDate dataNascimento,
							   @JsonProperty("ativa") Boolean ativa,
							   List<EnderecoRequestDTO> enderecos) implements Serializable {
}