package com.munir.crud_pessoa.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.munir.crud_pessoa.dtos.request.EnderecoRequestDTO;
import com.munir.crud_pessoa.dtos.response.EnderecoResponseDTO;
import com.munir.crud_pessoa.services.EnderecoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/endereco")
public class EnderecoController {
	
	private final EnderecoService enderecoService;	
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
				 consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EnderecoResponseDTO> save(@RequestBody EnderecoRequestDTO requestDTO) {

		EnderecoResponseDTO responseDTO = enderecoService.save(requestDTO);

		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}
	
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,
			 consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EnderecoResponseDTO> update(@RequestBody EnderecoRequestDTO requestDTO) {
	
		EnderecoResponseDTO responseDTO = enderecoService.update(requestDTO);
	
		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}
}
