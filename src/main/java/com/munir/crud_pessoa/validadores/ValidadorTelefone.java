package com.munir.crud_pessoa.validadores;

import org.springframework.stereotype.Component;

import com.munir.crud_pessoa.entidades.Telefone;
import com.munir.crud_pessoa.entidades.TipoTelefone;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ValidadorTelefone implements Validador<Telefone> {

	private final ValidadorCamposPreenchidosEntidades<Telefone> validadorCampos;	

	private final ValidadorCamposPreenchidosEntidades<TipoTelefone> validadorCamposTipoTelefone;	
	
	@Override
	public void validar(Telefone telefone) {
		
		validadorCampos.validar(telefone);
		validadorCamposTipoTelefone.validar(telefone.getTipoTelefone());
	}
}
