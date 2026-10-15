package com.munir.crud_pessoa.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.munir.crud_pessoa.dtos.request.AuthRequestDTO;
import com.munir.crud_pessoa.dtos.response.TokenResponseDTO;
import com.munir.crud_pessoa.security.services.JwtService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;
	
	private final AuthenticationManager authenticationManager;	
	
	@PostMapping(path = "/login",
				 produces = MediaType.APPLICATION_JSON_VALUE,
				 consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TokenResponseDTO> login(@RequestBody AuthRequestDTO request) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        UserDetails user = (UserDetails) authentication.getPrincipal();
        TokenResponseDTO token = jwtService.generateToken(user);

        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}
