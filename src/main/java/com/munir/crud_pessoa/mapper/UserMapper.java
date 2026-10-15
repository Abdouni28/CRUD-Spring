package com.munir.crud_pessoa.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.munir.crud_pessoa.dtos.request.UserRequestDTO;
import com.munir.crud_pessoa.dtos.response.UserResponseDTO;
import com.munir.crud_pessoa.security.entidades.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
		uses = {ProfileMapper.class})
public interface UserMapper extends BaseMapper<User, UserRequestDTO, UserResponseDTO> {
	
	@Override
	@Mapping(source = "person.id", target = "personId")
	UserResponseDTO toResponseDTO(User user);
	
	@Override
	@Mapping(source = "personId", target = "person.id")
	User toEntity(UserRequestDTO requestDTO);

	@Override
	@Mapping(source = "personId", target = "person.id")
	void toEntityUpdate(UserRequestDTO dto, @MappingTarget User user);
	
	default UserDetails usuarioToUserDetails(com.munir.crud_pessoa.security.entidades.User user) {
		
		List<SimpleGrantedAuthority> authorities = user.getProfiles()
								                       .stream()
									                   .map(perfil -> new SimpleGrantedAuthority(perfil.getName()))
									                   .toList();

        org.springframework.security.core.userdetails.User userDetails =
        		new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getActive(), true, true, true, authorities);
		
        return userDetails;
	}
}