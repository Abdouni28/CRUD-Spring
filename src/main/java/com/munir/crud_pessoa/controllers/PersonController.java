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

import com.munir.crud_pessoa.dtos.request.PersonRequestDTO;
import com.munir.crud_pessoa.dtos.request.filtros_busca.PersonSearchFiltersRequestDTO;
import com.munir.crud_pessoa.dtos.response.PersonResponseDTO;
import com.munir.crud_pessoa.services.PersonService;
import com.munir.crud_pessoa.utils.MessagesLoader;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {
	
	private final PersonService personService;	
	
	@GetMapping(path = "/{id}",
				produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findById(@PathVariable Long id) {
		
		PersonResponseDTO response = personService.findById(id);
		
		if(response == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					MessageFormat.format(MessagesLoader.loadMessage("message.nenhuma_pessoa_encontrada_by_id"), id));
	
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	
	@GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
				produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PersonResponseDTO>> find(@RequestBody(required = false) PersonSearchFiltersRequestDTO request,
													  	Pageable peageable) {

		List<PersonResponseDTO> response = personService.find(request, peageable);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	  
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
				 produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonResponseDTO> save(@RequestBody PersonRequestDTO request) {

		PersonResponseDTO response = personService.save(request);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	  
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
				produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonResponseDTO> update(@RequestBody PersonRequestDTO request) {

		PersonResponseDTO response = personService.update(request);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	  
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping(path = "/{id}",
				   produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity delete(@PathVariable Long id) {
	  
		personService.delete(id);
	  
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}