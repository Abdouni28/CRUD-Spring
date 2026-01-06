package com.munir.crud_pessoa.dtos.response;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

public class PessoaResponseDTO extends RepresentationModel<PessoaResponseDTO> implements Serializable {

	private static final long serialVersionUID = -6038011949225560568L;

	@Getter
	@Setter
	private Long id;
	
	@Getter
	@Setter
	private String nome;
	
	@Getter
	@Setter
	private String cpf;
	
	@Getter
	@Setter
	private String email;
	
	@Getter
	@Setter
	private LocalDate dataNascimento;
	
	@Getter
	@Setter
	private String flagAtivo;
}