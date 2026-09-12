package com.munir.crud_pessoa.security.services;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.munir.crud_pessoa.dtos.response.UsuarioResponseDTO;
import com.munir.crud_pessoa.entidades.Pessoa;
import com.munir.crud_pessoa.exceptions.UsuarioValidationException;
import com.munir.crud_pessoa.mapper.UsuarioMapper;
import com.munir.crud_pessoa.security.entidades.Perfil;
import com.munir.crud_pessoa.security.entidades.Perfil.PerfilENUM;
import com.munir.crud_pessoa.security.entidades.Usuario;
import com.munir.crud_pessoa.security.repositories.UsuarioRepository;
import com.munir.crud_pessoa.utils.MessagesLoader;

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
	
	public UsuarioResponseDTO adicionarPerfis(Long idUsuario, Set<PerfilENUM> perfis) {
		
		Optional<Usuario> optionalUsuario = repository.findById(idUsuario);
		
		if(optionalUsuario.isPresent()) {
			
			Usuario usuario = optionalUsuario.get();
			
			perfis.forEach(perfil -> {
				
				if(usuarioPossuiPerfil(usuario, perfil).equals(Boolean.FALSE)) {
					
					usuario.getPerfis().add(new Perfil(perfil.getId(), perfil.getNome()));
					
				} else {
					
					throw new UsuarioValidationException(MessageFormat.format(MessagesLoader.loadMessage("message.usuario_ja_possui_perfil"),
														 usuario.getNomeUsuario(), perfil.getNome()));
				}
			});
			
			Usuario usuarioSalvo = repository.save(usuario);
			
			UsuarioResponseDTO responseDTO = usuarioMapper.toResponseDTO(usuarioSalvo);
			
			return responseDTO;
		}
		
		return null;
	}
	
	public UsuarioResponseDTO removerPerfis(Long idUsuario, Set<PerfilENUM> perfis) {
		
		Optional<Usuario> optionalUsuario = repository.findById(idUsuario);
		
		if(optionalUsuario.isPresent()) {
			
			Usuario usuario = optionalUsuario.get();
			
			perfis.forEach(perfil -> {
				
				if(usuarioPossuiPerfil(usuario, perfil).equals(Boolean.TRUE)) {
					
					usuario.getPerfis().removeIf(perfilRemover -> perfilRemover.getId().equals(perfil.getId()));
					
				} else {
					
					throw new UsuarioValidationException(MessageFormat.format(MessagesLoader.loadMessage("message.usuario_nao_possui_perfil"),
														 usuario.getNomeUsuario(), perfil.getNome()));
				}
			});
			
			Usuario usuarioSalvo = repository.save(usuario);			
			UsuarioResponseDTO responseDTO = usuarioMapper.toResponseDTO(usuarioSalvo);
			
			return responseDTO;
		}
		
		return null;
	}
	
	private Boolean usuarioPossuiPerfil(Usuario usuario, PerfilENUM perfilENUM) {
		
		return usuario.getPerfis().stream().anyMatch(perfil -> perfil.getId().equals(perfilENUM.getId()));
	}
}