package com.munir.crud_pessoa.security.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.munir.crud_pessoa.entidades.Pessoa;
import com.munir.crud_pessoa.mapper.UsuarioMapper;
import com.munir.crud_pessoa.security.entidades.Perfil;
import com.munir.crud_pessoa.security.entidades.Perfil.PerfilENUM;
import com.munir.crud_pessoa.security.entidades.Usuario;
import com.munir.crud_pessoa.security.repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {
	
	private final UsuarioMapper usuarioMapper;
	
	private final UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String nomeUsuario) throws UsernameNotFoundException {

		Optional<Usuario> optionalUsuario = repository.findByNomeUsuarioAndAtivoTrue(nomeUsuario);

		if (optionalUsuario.isPresent())			
			return usuarioMapper.usuarioToUserDetails(optionalUsuario.get());
		
		throw new UsernameNotFoundException("Usuário não encontrado: " + nomeUsuario);
	}    
	
	public void criarUsuario(Pessoa pessoa) {
		
		String nomeUsuario = pessoa.getEmail().split("@")[0];
		
		Set<Perfil> perfis = Set.of(new Perfil(PerfilENUM.PESSOA.getId(), PerfilENUM.PESSOA.getNome()));
		
		Usuario usuario = new Usuario(null, nomeUsuario, "", LocalDateTime.now(), true, perfis, pessoa);
		usuario = repository.save(usuario);
		
		pessoa.setUsuario(usuario);
	}
}