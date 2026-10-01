package com.munir.crud_pessoa.security.services;

import java.text.MessageFormat;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.munir.crud_pessoa.dtos.response.UsuarioResponseDTO;
import com.munir.crud_pessoa.exceptions.UsuarioValidationException;
import com.munir.crud_pessoa.mapper.UsuarioMapper;
import com.munir.crud_pessoa.security.entidades.Perfil;
import com.munir.crud_pessoa.security.entidades.Usuario;
import com.munir.crud_pessoa.security.entidades.Perfil.PerfilENUM;
import com.munir.crud_pessoa.security.repositories.UsuarioRepository;
import com.munir.crud_pessoa.utils.MessagesLoader;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserProfileService {	

	private final UsuarioMapper usuarioMapper;
	
	private final UsuarioRepository repository;

	public UsuarioResponseDTO addProfiles(Long idUser, Set<PerfilENUM> profiles) {
		
		Optional<Usuario> optionalUser = repository.findById(idUser);
		
		if(optionalUser.isPresent()) {
			
			Usuario user = optionalUser.get();
			
			profiles.forEach(profile -> {
				
				if(userHasProfile(user, profile).equals(Boolean.FALSE)) {
					
					user.getPerfis().add(new Perfil(profile.getId(), profile.getNome()));
					
				} else {
					
					throw new UsuarioValidationException(MessageFormat.format(MessagesLoader.loadMessage("message.usuario_ja_possui_perfil"),
													 	 user.getNomeUsuario(), profile.getNome()));
				}
			});
			
			Usuario savedUser = repository.save(user);
			
			UsuarioResponseDTO responseDTO = usuarioMapper.toResponseDTO(savedUser);
			
			return responseDTO;
		}
		
		return null;
	}
	
	public UsuarioResponseDTO removeProfiles(Long idUser, Set<PerfilENUM> profiles) {
		
		Optional<Usuario> optionalUser = repository.findById(idUser);
		
		if(optionalUser.isPresent()) {
			
			Usuario user = optionalUser.get();
			
			profiles.forEach(profile -> {
				
				if(userHasProfile(user, profile).equals(Boolean.TRUE)) {
					
					user.getPerfis().removeIf(perfilRemover -> perfilRemover.getId().equals(profile.getId()));
					
				} else {
					
					throw new UsuarioValidationException(MessageFormat.format(MessagesLoader.loadMessage("message.usuario_nao_possui_perfil"),
														 user.getNomeUsuario(), profile.getNome()));
				}
			});
			
			Usuario savedUser = repository.save(user);

			UsuarioResponseDTO responseDTO = usuarioMapper.toResponseDTO(savedUser);
			
			return responseDTO;
		}
		
		return null;
	}
	
	private Boolean userHasProfile(Usuario user, PerfilENUM profile) {
		
		return user.getPerfis().stream().anyMatch(perfil -> perfil.getId().equals(profile.getId()));
	}
}
