package com.munir.crud_pessoa.validadores;

import org.springframework.stereotype.Component;

import com.munir.crud_pessoa.entidades.Endereco;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ValidadorEndereco implements Validador<Endereco> {

	private final ValidadorCamposPreenchidosEntidades<Endereco> validadorCampos;	
	
	@Override
	public void validar(Endereco endereco) {
		
		validadorCampos.validar(endereco);
	}
}
