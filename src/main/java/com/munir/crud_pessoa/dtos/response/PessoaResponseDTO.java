package com.munir.crud_pessoa.dtos.response;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PessoaResponseDTO(Long id, String nome, String cpf, String email,
								@JsonProperty("data_nascimento") LocalDate dataNascimento,
								@JsonProperty("ativa") Boolean ativa)implements Serializable {

	private static final long serialVersionUID = -6038011949225560568L;
}
/*
 * @Getter
 * 
 * @Setter private Long id;
 * 
 * @Getter
 * 
 * @Setter private String nome;
 * 
 * @Getter
 * 
 * @Setter private String cpf;
 * 
 * @Getter
 * 
 * @Setter private String email;
 * 
 * @Getter
 * 
 * @Setter private LocalDate dataNascimento;
 * 
 * @Getter
 * 
 * @Setter private String flagAtivo;
 */