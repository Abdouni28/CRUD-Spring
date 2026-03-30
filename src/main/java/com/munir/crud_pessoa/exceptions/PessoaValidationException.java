package com.munir.crud_pessoa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PessoaValidationException extends RuntimeException{

	private static final long serialVersionUID = -7642318694345390099L;

	@Getter
	@Setter
	private String mensagem;
	
	public PessoaValidationException(String mensagem) {		
		super(mensagem);
	}
}
