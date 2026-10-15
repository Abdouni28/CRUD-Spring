package com.munir.crud_pessoa.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.munir.crud_pessoa.dtos.request.AuditRequestDTO;
import com.munir.crud_pessoa.dtos.response.AuditResponseDTO;
import com.munir.crud_pessoa.entidades.Address;
import com.munir.crud_pessoa.entidades.Person;
import com.munir.crud_pessoa.entidades.Telephone;
import com.munir.crud_pessoa.security.entidades.User;
import com.munir.crud_pessoa.services.AuditService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/audit")
public class AuditController {
	
	private final AuditService auditService;	
	
	@GetMapping(path = "/person",
				produces = MediaType.APPLICATION_JSON_VALUE,
				consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AuditResponseDTO>> getPersonAudit(@RequestBody AuditRequestDTO request) {

		List<AuditResponseDTO> responseDTO = auditService.getAudit(request, Person.class);

		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}
	
	@GetMapping(path = "/address",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AuditResponseDTO>> getAddressAudit(@RequestBody AuditRequestDTO request) {
	
		List<AuditResponseDTO> responseDTO = auditService.getAudit(request, Address.class);
	
		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}
	
	@GetMapping(path = "/telephone",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AuditResponseDTO>> getTelephoneAudit(@RequestBody AuditRequestDTO request) {
	
		List<AuditResponseDTO> responseDTO = auditService.getAudit(request, Telephone.class);
	
		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}
	
	@GetMapping(path = "/user",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AuditResponseDTO>> getUserAudit(@RequestBody AuditRequestDTO request) {
	
		List<AuditResponseDTO> responseDTO = auditService.getAudit(request, User.class);
	
		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}
}