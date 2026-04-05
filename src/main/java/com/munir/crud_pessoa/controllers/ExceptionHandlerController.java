package com.munir.crud_pessoa.controllers;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.munir.crud_pessoa.dtos.response.ExceptionResponseDTO;
import com.munir.crud_pessoa.exceptions.PessoaValidationException;

import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlerController {
	
	private ExceptionResponseDTO responseDTO;
	
	LocalDateTime timestamp;
	
	String mensagem;
	
	String nomeException;

	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PessoaValidationException.class)
    public ResponseEntity<ExceptionResponseDTO> handlePessoaValidationException(PessoaValidationException exception) {
		
		setResponseDTO(exception);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
    }
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponseDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
		
		setResponseDTO(exception);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
    }
	
	private void setResponseDTO(Exception exception) {
		
		this.timestamp = LocalDateTime.now();
		this.mensagem = exception.getMessage();
		this.nomeException = exception.getClass().getSimpleName();
		 
		this.responseDTO = new ExceptionResponseDTO(timestamp, mensagem, nomeException);
	}
}