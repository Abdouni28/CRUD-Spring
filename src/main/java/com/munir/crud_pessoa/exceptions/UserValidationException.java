package com.munir.crud_pessoa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserValidationException extends RuntimeException {

	private static final long serialVersionUID = 1571142264404557574L;
	
	@Getter
	@Setter
	private String mensagem;
	
	public UserValidationException(String message) {		
		super(message);
	}
}