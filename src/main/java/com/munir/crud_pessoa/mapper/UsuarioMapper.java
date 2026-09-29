package com.munir.crud_pessoa.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.munir.crud_pessoa.dtos.request.UsuarioRequestDTO;
import com.munir.crud_pessoa.dtos.response.UsuarioResponseDTO;
import com.munir.crud_pessoa.security.entidades.Usuario;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
		uses = {PerfilMapper.class})
public interface UsuarioMapper extends BaseMapper<Usuario, UsuarioRequestDTO, UsuarioResponseDTO> {
	
	@Override
	@Mapping(source = "pessoa.id", target = "idPessoa")
	UsuarioResponseDTO toResponseDTO(Usuario usuario);
	
	@Override
	@Mapping(source = "idPessoa", target = "pessoa.id")
	Usuario toEntity(UsuarioRequestDTO requestDTO);

	@Override
	@Mapping(source = "idPessoa", target = "pessoa.id")
	void toEntityUpdate(UsuarioRequestDTO dto, @MappingTarget Usuario entity);
	
	default UserDetails usuarioToUserDetails(Usuario usuario) {
		
		List<SimpleGrantedAuthority> authorities = usuario.getPerfis()
									                      .stream()
									                      .map(perfil -> new SimpleGrantedAuthority(perfil.getNome()))
									                      .toList();

        User userDetails = new User(usuario.getNomeUsuario(), usuario.getSenha(), usuario.getAtivo(), true, true, true, authorities);
		
        return userDetails;
	}
}