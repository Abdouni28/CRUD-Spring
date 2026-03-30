package com.munir.crud_pessoa.controllers;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.munir.crud_pessoa.dtos.response.ExceptionResponseDTO;
import com.munir.crud_pessoa.exceptions.PessoaValidationException;

@RestControllerAdvice
public class ExceptionHandlerController {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PessoaValidationException.class)
    public ResponseEntity<ExceptionResponseDTO> handlePessoaValidationException(PessoaValidationException exception) {
		
		LocalDateTime timestamp = LocalDateTime.now();
		String mensagem = exception.getMessage();
		String nomeException = exception.getClass().getSimpleName();
		
		ExceptionResponseDTO responseDTO = new ExceptionResponseDTO(timestamp, mensagem, nomeException);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
    }
}
