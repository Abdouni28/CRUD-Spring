package com.munir.crud_pessoa.services;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.munir.crud_pessoa.dtos.request.PageableSortValidationRequestDTO;
import com.munir.crud_pessoa.dtos.request.PersonRequestDTO;
import com.munir.crud_pessoa.dtos.request.filtros_busca.PersonSearchFiltersRequestDTO;
import com.munir.crud_pessoa.dtos.response.PersonResponseDTO;
import com.munir.crud_pessoa.email_templates.EmailTemplate;
import com.munir.crud_pessoa.email_templates.NewPersonEmailTemplate;
import com.munir.crud_pessoa.entidades.Person;
import com.munir.crud_pessoa.exceptions.PersonValidationException;
import com.munir.crud_pessoa.mapper.PersonMapper;
import com.munir.crud_pessoa.repositories.PersonRepository;
import com.munir.crud_pessoa.security.services.UserService;
import com.munir.crud_pessoa.utils.MessagesLoader;
import com.munir.crud_pessoa.validadores.PageableSortValidator;
import com.munir.crud_pessoa.validadores.PersonValidator;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PersonService {
	
	private final PersonMapper mapper;	
	
	private final PersonValidator validator;
	private final PageableSortValidator pageableValidator;
	
	private final EmailService emailService;	
	private final UserService usuarioService;
	
	private final PersonRepository repository;
	
	public PersonResponseDTO findById(Long id) {
		
		Optional<Person> optionalPerson = repository.findById(id);
		
		if(optionalPerson.isPresent()) {
			
			PersonResponseDTO response = mapper.toResponseDTO(optionalPerson.get());
			
			return response;
		}
				
		return null;
	}
	
	public List<PersonResponseDTO> find(PersonSearchFiltersRequestDTO request, Pageable pageable) {
	  
		List<Person> people;
		List<PersonResponseDTO> response = new ArrayList<>();
	  
		if (request == null) {
	  
			people = repository.findAll();
	  
		} else {
			
			Set<String> sortProperties = Set.of("id", "name", "cpf", "email", "birthDate");
			
			PageableSortValidationRequestDTO pageableSortValidationRequestDTO = new PageableSortValidationRequestDTO(pageable, sortProperties);
			pageableValidator.validate(pageableSortValidationRequestDTO);
			
			people = repository.find(request, pageable);
		}
		
		response = mapper.toResponseDTOList(people);
		  
		return response;
	}
	  
	public PersonResponseDTO save(PersonRequestDTO request) {
	
		Person person = mapper.toEntity(request);
		
		//validator.validate(pessoa);
		
		person.getAddresses().forEach(address -> address.setPerson(person));
		person.getTelephones().forEach(telephone -> telephone.setPerson(person));
		
		usuarioService.createUser(person);
		
		repository.save(person);
		
		PersonResponseDTO response = mapper.toResponseDTO(person);
		
		EmailTemplate emailTemplate = new NewPersonEmailTemplate(response);
		emailService.sendToQueue(emailTemplate);	
	
		return response;
	}

	@CacheEvict(value = "audit", allEntries = true)
    public PersonResponseDTO update(PersonRequestDTO request) {
    	
    	Optional<Person> optionalPerson = repository.findById(request.id());
    	
    	if(optionalPerson.isEmpty()) 
			throw new PersonValidationException(MessageFormat.format(MessagesLoader.loadMessage("message.nenhuma_pessoa_encontrada_by_id"),
					request.id()));				
    	
    	Person person = optionalPerson.get();
    	
    	mapper.toEntityUpdate(request, person);		 
    	
    	person.getUser().setPassword("Test");
    	repository.save(person);
    	
    	PersonResponseDTO response = findById(request.id());
  
    	return response;
    }
	  
	
	public void delete(Long id) {
		
		PersonResponseDTO response = findById(id);

		if (response == null)
			throw new PersonValidationException(MessageFormat.format(MessagesLoader.loadMessage("message.nenhuma_pessoa_encontrada_by_id"),	id));
		
		repository.deleteById(id);
	}
}