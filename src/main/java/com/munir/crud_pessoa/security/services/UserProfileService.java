package com.munir.crud_pessoa.security.services;

import java.text.MessageFormat;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.munir.crud_pessoa.dtos.response.UserResponseDTO;
import com.munir.crud_pessoa.exceptions.UserValidationException;
import com.munir.crud_pessoa.mapper.UserMapper;
import com.munir.crud_pessoa.security.entidades.Profile;
import com.munir.crud_pessoa.security.entidades.Profile.ProfileENUM;
import com.munir.crud_pessoa.security.entidades.User;
import com.munir.crud_pessoa.security.repositories.UsuarioRepository;
import com.munir.crud_pessoa.utils.MessagesLoader;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserProfileService {	

	private final UserMapper mapper;
	
	private final UsuarioRepository repository;

	public UserResponseDTO addProfiles(Long id, Set<ProfileENUM> profiles) {
		
		Optional<User> optionalUser = repository.findById(id);
		
		if(optionalUser.isPresent()) {
			
			User user = optionalUser.get();
			
			profiles.forEach(profile -> {
				
				if(userHasProfile(user, profile).equals(Boolean.FALSE)) {
					
					user.getProfiles().add(new Profile(profile.getId(), profile.getName()));
					
				} else {
					
					throw new UserValidationException(MessageFormat.format(MessagesLoader.loadMessage("message.usuario_ja_possui_perfil"),
													 	 user.getUsername(), profile.getName()));
				}
			});
			
			User savedUser = repository.save(user);
			
			UserResponseDTO response = mapper.toResponseDTO(savedUser);
			
			return response;
		}
		
		return null;
	}
	
	public UserResponseDTO removeProfiles(Long id, Set<ProfileENUM> profiles) {
		
		Optional<User> optionalUser = repository.findById(id);
		
		if(optionalUser.isPresent()) {
			
			User user = optionalUser.get();
			
			profiles.forEach(profile -> {
				
				if(userHasProfile(user, profile).equals(Boolean.TRUE)) {
					
					user.getProfiles().removeIf(profileToRemove -> profileToRemove.getId().equals(profile.getId()));
					
				} else {
					
					throw new UserValidationException(MessageFormat.format(MessagesLoader.loadMessage("message.usuario_nao_possui_perfil"),
														 user.getUsername(), profile.getName()));
				}
			});
			
			User savedUser = repository.save(user);

			UserResponseDTO response = mapper.toResponseDTO(savedUser);
			
			return response;
		}
		
		return null;
	}
	
	private Boolean userHasProfile(User user, ProfileENUM profile) {
		
		return user.getProfiles().stream().anyMatch(perfil -> perfil.getId().equals(profile.getId()));
	}
}