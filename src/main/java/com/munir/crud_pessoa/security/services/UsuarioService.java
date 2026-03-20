package com.munir.crud_pessoa.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.munir.crud_pessoa.security.entidades.Usuario;
import com.munir.crud_pessoa.security.repositories.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String nomeUsuario) throws UsernameNotFoundException {

		Optional<Usuario> optionalUsuario = usuarioRepository.findByNomeUsuarioAndAtivoTrue(nomeUsuario);

		if (optionalUsuario.isPresent()) {
			
			Usuario usuario = optionalUsuario.get();
			
			List<SimpleGrantedAuthority> authorities = usuario.getPerfis()
										                      .stream()
										                      .map(perfil -> new SimpleGrantedAuthority(perfil.getNome()))
										                      .toList();

	        User usuarioAplicacao = new User(usuario.getNomeUsuario(), usuario.getSenha(), usuario.getAtivo(), true, true, true, authorities);
			
	        return usuarioAplicacao;
		}
		
		throw new UsernameNotFoundException("Usuário não encontrado: " + nomeUsuario);
	}
}