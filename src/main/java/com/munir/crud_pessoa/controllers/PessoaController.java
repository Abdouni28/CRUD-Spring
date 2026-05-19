package com.munir.crud_pessoa.controllers;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.munir.crud_pessoa.dtos.request.PessoaRequestDTO;
import com.munir.crud_pessoa.dtos.request.filtros_busca.FiltrosBuscaPessoaRequestDTO;
import com.munir.crud_pessoa.dtos.response.PessoaResponseDTO;
import com.munir.crud_pessoa.services.PessoaService;
import com.munir.crud_pessoa.utils.MessagesLoader;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pessoa")
public class PessoaController {
	
	private final PessoaService pessoaService;	
	
	@GetMapping(path = "/{id}",
				produces = { MediaType.APPLICATION_JSON_VALUE,
							MediaType.APPLICATION_XML_VALUE,
							MediaType.APPLICATION_YAML_VALUE })
	public ResponseEntity<?> findById(@PathVariable("id") Long idPessoa) {
		
		PessoaResponseDTO responseDTO = pessoaService.findById(idPessoa);
		
		if(responseDTO == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					MessageFormat.format(MessagesLoader.loadMessage("message.nenhuma_pessoa_encontrada_by_id"), idPessoa));
	
		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}
	
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE,
							MediaType.APPLICATION_XML_VALUE,
							MediaType.APPLICATION_YAML_VALUE },
				consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PessoaResponseDTO>> find(@RequestBody(required = false) FiltrosBuscaPessoaRequestDTO requestDTO,
													  	Pageable peageable) {

		List<PessoaResponseDTO> responseDTO = pessoaService.find(requestDTO, peageable);

		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}
	  
	
	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE,
						 	  MediaType.APPLICATION_XML_VALUE,
						 	  MediaType.APPLICATION_YAML_VALUE },
				 consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PessoaResponseDTO> save(@RequestBody PessoaRequestDTO requestDTO) {

		PessoaResponseDTO responseDTO = pessoaService.save(requestDTO);

		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
	}
	  
	
	@PutMapping(produces = { MediaType.APPLICATION_JSON_VALUE,
							 MediaType.APPLICATION_XML_VALUE,
						 	 MediaType.APPLICATION_YAML_VALUE },
				consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PessoaResponseDTO> update(@RequestBody PessoaRequestDTO requestDTO) {

		PessoaResponseDTO responseDTO = pessoaService.update(requestDTO);

		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}
	  
	
	@DeleteMapping(path = "/{id}",
				   produces = {MediaType.APPLICATION_JSON_VALUE,
						   	   MediaType.APPLICATION_XML_VALUE,
						   	   MediaType.APPLICATION_YAML_VALUE})
	public ResponseEntity<String> delete(@PathVariable("id") Long idPessoa) {
	  
		pessoaService.delete(idPessoa);
	  
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
