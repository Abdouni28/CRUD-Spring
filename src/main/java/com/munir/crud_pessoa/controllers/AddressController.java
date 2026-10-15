package com.munir.crud_pessoa.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.munir.crud_pessoa.dtos.request.AddressRequestDTO;
import com.munir.crud_pessoa.dtos.response.AddressResponseDTO;
import com.munir.crud_pessoa.services.AddressService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {
	
	private final AddressService addressService;	
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
				 consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AddressResponseDTO> insert(@RequestBody AddressRequestDTO requestDTO) {

		AddressResponseDTO responseDTO = addressService.insert(requestDTO);

		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}
	
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,
			 	consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AddressResponseDTO> update(@RequestBody AddressRequestDTO requestDTO) {
	
		AddressResponseDTO responseDTO = addressService.update(requestDTO);
	
		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}
}