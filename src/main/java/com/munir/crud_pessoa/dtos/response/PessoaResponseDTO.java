package com.munir.crud_pessoa.dtos.response;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PessoaResponseDTO(Long id, String nome, String cpf, String email,
								@JsonProperty("data_nascimento") LocalDate dataNascimento,
								@JsonProperty("ativa") Boolean ativa,
								List<EnderecoResponseDTO> enderecos, List<TelefoneResponseDTO> telefones,
								UsuarioResponseDTO usuario) implements Serializable {

}