package com.munir.crud_pessoa.validadores;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.munir.crud_pessoa.dtos.request.DocumentValidationRequestDTO;
import com.munir.crud_pessoa.entidades.Address;
import com.munir.crud_pessoa.entidades.Person;
import com.munir.crud_pessoa.entidades.Telephone;
import com.munir.crud_pessoa.enums.DocumentTypeENUM;
import com.munir.crud_pessoa.enums.RegexENUM;
import com.munir.crud_pessoa.exceptions.PersonValidationException;
import com.munir.crud_pessoa.repositories.PersonRepository;
import com.munir.crud_pessoa.utils.ListUtils;
import com.munir.crud_pessoa.utils.MessagesLoader;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PersonValidator implements Validator<Person> {
	
	private static final String EMPTY_SPACE = " ";
	private static final String BLANK = "";
	private static final String LEFT_PARENTHESIS = "(";
	private static final String RIGHT_PARENTHESIS = ")";
	private static final String HYPHEN = "-";

	private final FilledFieldsValidator<Address> addressFieldsValidator;

	private final FilledFieldsValidator<Telephone> telephoneFieldsValidator;
	
	private final DocumentValidator documentValidator;
	
	private final PersonRepository repository;	
	
	@Override
	public void validate(Person person) {
		
		validateName(person);
		//validateUniqueCPF(person);
		validateEmail(person);
		validateAge(person);
		validateAddresses(person);
		validateTelephones(person);
		
		DocumentValidationRequestDTO documentValidationRequestDTO = new DocumentValidationRequestDTO(person.getCpf(), DocumentTypeENUM.CPF);
		documentValidator.validate(documentValidationRequestDTO);
	}

	private void validateName(Person person) {
		
		if(!person.getName().matches(RegexENUM.CHARS_ONLY.getValue()))
			throw new PersonValidationException(MessagesLoader.loadMessage("message.apenas_caracteres_nome"));
		
		List<String> names = Arrays.asList(person.getName().split(EMPTY_SPACE));		
		int minimumSize = 2;
		
		if(names.size() < minimumSize)
			throw new PersonValidationException(MessagesLoader.loadMessage("message.nome_obrigatorio"));
		
		names.forEach(name -> {
			
			if(name.length() < minimumSize)
				throw new PersonValidationException(MessagesLoader.loadMessage("message.tamanho_nome_obrigatorio"));
		});
		
		person.setName(person.getName().toUpperCase());
	}
	
	private void validateUniqueCPF(Person person) {
		
		Optional<Person> optionalPerson = repository.findByCpfAndActiveTrue(person.getCpf());
		
		if(optionalPerson.isPresent())
			throw new PersonValidationException(MessagesLoader.loadMessage("message.cpf_duplicado"));
	}
	
	private void validateEmail(Person person) {
		
		if(!person.getEmail().matches(RegexENUM.EMAIL_PATTERN.getValue()))
			throw new PersonValidationException(MessagesLoader.loadMessage("message.formato_email_invalido"));
	}
	
	private void validateAge(Person person) {
		
		LocalDate now = LocalDate.now();
		LocalDate birthDate = person.getBirthDate();
		
		Long majority = 18L;
		
		if(ChronoUnit.YEARS.between(birthDate, now) < majority)
			throw new PersonValidationException(MessagesLoader.loadMessage("message.maioridade_obrigatoria"));
	}
	
	private void validateAddresses(Person person) {
		
		if(ListUtils.isNullOrEmpty(person.getAddresses()).equals(Boolean.TRUE))
			throw new PersonValidationException(MessagesLoader.loadMessage("message.endereco_obrigatorio"));		

		person.getAddresses().forEach(address -> addressFieldsValidator.validate(address));
	}
	
	private void validateTelephones(Person person) {
		
		if(ListUtils.isNullOrEmpty(person.getTelephones()).equals(Boolean.TRUE))
			throw new PersonValidationException(MessagesLoader.loadMessage("message.telefone_obrigatorio"));
		
		person.getTelephones().forEach(telephone -> {
			
			telephoneFieldsValidator.validate(telephone);
			
			if(!telephone.getNumber().matches(RegexENUM.NUMBERS_ONLY.getValue()))
				throw new PersonValidationException(MessagesLoader.loadMessage("message.telefone_somente_numeros"));
			
			int minimumSize = 10;
			if(telephone.getNumber().length() < minimumSize)
				throw new PersonValidationException(MessagesLoader.loadMessage("message.tamanho_telefone_obrigatorio"));
			
			telephone.setNumber(telephone.getNumber().replace(LEFT_PARENTHESIS, BLANK));
			telephone.setNumber(telephone.getNumber().replace(RIGHT_PARENTHESIS, BLANK));
			telephone.setNumber(telephone.getNumber().replace(HYPHEN, BLANK));
		});
	}
}