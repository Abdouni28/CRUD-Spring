package com.munir.crud_pessoa.enums;

import lombok.Getter;

@Getter
public enum RegexENUM {
	
	APENAS_CARACTERES("^[\\p{L} ]+$"),
	APENAS_NUMEROS("^\\d+$"),
	FORMATO_EMAIL("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

	private String valor;

	RegexENUM(String valor) {

		this.valor = valor;
	}
}
