package com.munir.crud_pessoa.security.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.munir.crud_pessoa.entidades.Person;
import com.munir.crud_pessoa.mapper.UserMapper;
import com.munir.crud_pessoa.security.entidades.Profile;
import com.munir.crud_pessoa.security.entidades.Profile.ProfileENUM;
import com.munir.crud_pessoa.security.entidades.User;
import com.munir.crud_pessoa.security.repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
	
	private final UserMapper mapper;
	
	private final UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> optionalUser = repository.findByUsernameAndActiveTrue(username);

		if (optionalUser.isPresent())			
			return mapper.usuarioToUserDetails(optionalUser.get());
		
		throw new UsernameNotFoundException("Usuário não encontrado: " + username);
	}    
	
	public void createUser(Person person) {
		
		String username = person.getEmail().split("@")[0];
		
		Set<Profile> perfis = Set.of(new Profile(ProfileENUM.PERSON.getId(), ProfileENUM.PERSON.getName()));
		
		User user = new User(null, username, "", LocalDateTime.now(), true, perfis, person);
		user = repository.save(user);
		
		person.setUser(user);
	}
}