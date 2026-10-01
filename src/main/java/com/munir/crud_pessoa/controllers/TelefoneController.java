package com.munir.crud_pessoa.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.munir.crud_pessoa.dtos.request.TelefoneRequestDTO;
import com.munir.crud_pessoa.dtos.response.TelefoneResponseDTO;
import com.munir.crud_pessoa.services.TelefoneService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/telefone")
public class TelefoneController {
	
	private final TelefoneService enderecoService;	
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
				 consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TelefoneResponseDTO> save(@RequestBody TelefoneRequestDTO requestDTO) {

		TelefoneResponseDTO responseDTO = enderecoService.save(requestDTO);

		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}
	
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,
			 consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TelefoneResponseDTO> update(@RequestBody TelefoneRequestDTO requestDTO) {
	
		TelefoneResponseDTO responseDTO = enderecoService.update(requestDTO);
	
		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}
}
