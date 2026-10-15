package com.munir.crud_pessoa.validadores;

import org.springframework.stereotype.Component;

import com.munir.crud_pessoa.entidades.Address;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AddressValidator implements Validator<Address> {

	private final FilledFieldsValidator<Address> fieldsValidator;	
	
	@Override
	public void validate(Address address) {
		
		fieldsValidator.validate(address);
	}
}
