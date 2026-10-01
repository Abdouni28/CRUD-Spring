package com.munir.crud_pessoa.controllers;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.munir.crud_pessoa.dtos.response.UsuarioResponseDTO;
import com.munir.crud_pessoa.security.entidades.Perfil.PerfilENUM;
import com.munir.crud_pessoa.security.services.UserProfileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-profile")
public class UsuarioPerfilController {
	
	private final UserProfileService userProfileService;	
	
	@PutMapping(path = "/add-profiles/{id}",
				produces = MediaType.APPLICATION_JSON_VALUE,
				consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsuarioResponseDTO> addProfiles(@PathVariable("id") Long idUser, @RequestBody Set<PerfilENUM> profiles) {

		UsuarioResponseDTO responseDTO = userProfileService.addProfiles(idUser, profiles);

		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}
	
	@PutMapping(path = "/remove-profiles/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsuarioResponseDTO> removeProfiles(@PathVariable("id") Long idUser, @RequestBody Set<PerfilENUM> profiles) {
	
		UsuarioResponseDTO responseDTO = userProfileService.removeProfiles(idUser, profiles);
	
		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}
}
