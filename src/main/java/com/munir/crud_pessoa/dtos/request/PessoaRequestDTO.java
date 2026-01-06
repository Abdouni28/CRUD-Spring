package com.munir.crud_pessoa.dtos.request;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PessoaRequestDTO(Long id, String nome, String cpf, String email,
							   @JsonProperty("data_nascimento") LocalDate dataNascimento,
							   @JsonProperty("flag_ativo")String flagAtivo) implements Serializable {

	private static final long serialVersionUID = -1699673320879885526L;
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