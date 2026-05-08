package com.munir.crud_pessoa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UsuarioValidationException extends RuntimeException {

	private static final long serialVersionUID = 1571142264404557574L;
	
	@Getter
	@Setter
	private String mensagem;
	
	public UsuarioValidationException(String mensagem) {		
		super(mensagem);
	}
}
