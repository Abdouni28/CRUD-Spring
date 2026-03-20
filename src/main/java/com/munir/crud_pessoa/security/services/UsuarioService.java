package com.munir.crud_pessoa.security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.munir.crud_pessoa.mapper.UsuarioMapper;
import com.munir.crud_pessoa.security.entidades.Usuario;
import com.munir.crud_pessoa.security.repositories.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {
	
	@Autowired
	UsuarioMapper usuarioMapper;
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String nomeUsuario) throws UsernameNotFoundException {

		Optional<Usuario> optionalUsuario = usuarioRepository.findByNomeUsuarioAndAtivoTrue(nomeUsuario);

		if (optionalUsuario.isPresent())			
			return usuarioMapper.usuarioToUserDetails(optionalUsuario.get());
		
		throw new UsernameNotFoundException("Usuário não encontrado: " + nomeUsuario);
	}
}