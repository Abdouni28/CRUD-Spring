package com.munir.crud_pessoa.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.munir.crud_pessoa.dtos.request.TelephoneRequestDTO;
import com.munir.crud_pessoa.dtos.response.TelephoneResponseDTO;
import com.munir.crud_pessoa.services.TelephoneService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/telephone")
public class TelephoneController {
	
	private final TelephoneService telephoneService;	
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
				 consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TelephoneResponseDTO> save(@RequestBody TelephoneRequestDTO request) {

		TelephoneResponseDTO response = telephoneService.insert(request);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,
			 consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TelephoneResponseDTO> update(@RequestBody TelephoneRequestDTO request) {
	
		TelephoneResponseDTO responseDTO = telephoneService.update(request);
	
		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}
}