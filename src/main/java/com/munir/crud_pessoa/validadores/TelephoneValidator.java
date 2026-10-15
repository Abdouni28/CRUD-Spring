package com.munir.crud_pessoa.validadores;

import org.springframework.stereotype.Component;

import com.munir.crud_pessoa.entidades.Telephone;
import com.munir.crud_pessoa.entidades.TelephoneType;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TelephoneValidator implements Validator<Telephone> {

	private final FilledFieldsValidator<Telephone> fieldsValidator;	

	private final FilledFieldsValidator<TelephoneType> telephoneTypeFieldsValidator;	
	
	@Override
	public void validate(Telephone telephone) {
		
		fieldsValidator.validate(telephone);
		telephoneTypeFieldsValidator.validate(telephone.getTelephoneType());
	}
}