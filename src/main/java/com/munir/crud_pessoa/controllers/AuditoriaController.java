package com.munir.crud_pessoa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.munir.crud_pessoa.dtos.request.AuditoriaRequestDTO;
import com.munir.crud_pessoa.dtos.response.AuditoriaResponseDTO;
import com.munir.crud_pessoa.entidades.Endereco;
import com.munir.crud_pessoa.entidades.Pessoa;
import com.munir.crud_pessoa.entidades.Telefone;
import com.munir.crud_pessoa.services.AuditoriaService;

@RestController
@RequestMapping("/auditoria")
public class AuditoriaController {
	
	@Autowired
	AuditoriaService auditoriaService;
	
	
	@GetMapping(path = "/pessoa",
				produces = MediaType.APPLICATION_JSON_VALUE,
				consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AuditoriaResponseDTO>> findPessoa(@RequestBody AuditoriaRequestDTO requestDTO) {

		List<AuditoriaResponseDTO> responseDTO = auditoriaService.buscarRevisoes(requestDTO, Pessoa.class);

		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}
	
	@GetMapping(path = "/endereco",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AuditoriaResponseDTO>> findEndereco(@RequestBody AuditoriaRequestDTO requestDTO) {
	
		List<AuditoriaResponseDTO> responseDTO = auditoriaService.buscarRevisoes(requestDTO, Endereco.class);
	
		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}
	
	@GetMapping(path = "/telefone",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AuditoriaResponseDTO>> findTelefone(@RequestBody AuditoriaRequestDTO requestDTO) {
	
		List<AuditoriaResponseDTO> responseDTO = auditoriaService.buscarRevisoes(requestDTO, Telefone.class);
	
		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}
}
