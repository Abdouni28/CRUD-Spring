package com.munir.crud_pessoa.dtos.request;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class PessoaRequestDTO extends RepresentationModel<PessoaRequestDTO> implements Serializable {

	private static final long serialVersionUID = -1699673320879885526L;

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
