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

import com.munir.crud_pessoa.dtos.response.UserResponseDTO;
import com.munir.crud_pessoa.security.entidades.Profile.ProfileENUM;
import com.munir.crud_pessoa.security.services.UserProfileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-profile")
public class UserProfileController {
	
	private final UserProfileService userProfileService;	
	
	@PutMapping(path = "/add-profiles/{id}",
				produces = MediaType.APPLICATION_JSON_VALUE,
				consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponseDTO> addProfiles(@PathVariable Long id, @RequestBody Set<ProfileENUM> profiles) {

		UserResponseDTO response = userProfileService.addProfiles(id, profiles);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PutMapping(path = "/remove-profiles/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponseDTO> removeProfiles(@PathVariable Long id, @RequestBody Set<ProfileENUM> profiles) {
	
		UserResponseDTO response = userProfileService.removeProfiles(id, profiles);
	
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}