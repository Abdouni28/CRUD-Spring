package com.munir.crud_pessoa.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.munir.crud_pessoa.security.entidades.Usuario;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class UsuarioMapper {	

	public UserDetails usuarioToUserDetails(Usuario usuario) {
		
		List<SimpleGrantedAuthority> authorities = usuario.getPerfis()
									                      .stream()
									                      .map(perfil -> new SimpleGrantedAuthority(perfil.getNome()))
									                      .toList();

        User userDetails = new User(usuario.getNomeUsuario(), usuario.getSenha(), usuario.getAtivo(), true, true, true, authorities);
		
        return userDetails;
	}
}