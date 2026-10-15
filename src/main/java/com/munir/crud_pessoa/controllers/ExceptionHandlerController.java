package com.munir.crud_pessoa.controllers;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.munir.crud_pessoa.dtos.response.ExceptionResponseDTO;
import com.munir.crud_pessoa.exceptions.PersonValidationException;
import com.munir.crud_pessoa.exceptions.UserValidationException;

import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlerController {
	
	private ExceptionResponseDTO response;
	
	private LocalDateTime timestamp;
	
	private String message;
	
	private String exceptionName;

	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PersonValidationException.class)
    public ResponseEntity<ExceptionResponseDTO> handlePessoaValidationException(PersonValidationException exception) {
		
		setResponseDTO(exception);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserValidationException.class)
    public ResponseEntity<ExceptionResponseDTO> handleUsuarioValidationException(UserValidationException exception) {
		
		setResponseDTO(exception);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponseDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
		
		setResponseDTO(exception);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponseDTO> handleIllegalArgumentException(IllegalArgumentException exception) {
		
		setResponseDTO(exception);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionResponseDTO> handleNullPointerExceptionException(NullPointerException exception) {
		
		setResponseDTO(exception);
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
	
	private void setResponseDTO(Exception exception) {
		
		this.timestamp = LocalDateTime.now();
		this.message = exception.getMessage();
		this.exceptionName = exception.getClass().getSimpleName();
		 
		this.response = new ExceptionResponseDTO(timestamp, message, exceptionName);
	}
}