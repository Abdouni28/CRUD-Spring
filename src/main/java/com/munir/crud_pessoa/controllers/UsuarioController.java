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
import com.munir.crud_pessoa.security.services.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {
	
	private final UsuarioService usuarioService;	
	
	@PutMapping(path = "/add-perfis/{id}",
				produces = MediaType.APPLICATION_JSON_VALUE,
				consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsuarioResponseDTO> adicionarPerfis(@PathVariable("id") Long idUsuario, @RequestBody Set<PerfilENUM> perfis) {

		UsuarioResponseDTO responseDTO = usuarioService.adicionarPerfis(idUsuario, perfis);

		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}
	
	@PutMapping(path = "/remove-perfis/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsuarioResponseDTO> removerPerfis(@PathVariable("id") Long idUsuario, @RequestBody Set<PerfilENUM> perfis) {
	
		UsuarioResponseDTO responseDTO = usuarioService.removerPerfis(idUsuario, perfis);
	
		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}
}
