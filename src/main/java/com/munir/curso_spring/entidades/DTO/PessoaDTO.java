package com.munir.curso_spring.entidades.DTO;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO extends RepresentationModel<PessoaDTO> implements Serializable {

	private static final long serialVersionUID = -1699673320879885526L;

	@Getter
	@Setter
	private Long id;
	
	@Getter
	@Setter
	private String nome;
	
	@Getter
	@Setter
	private String endereco;
}
