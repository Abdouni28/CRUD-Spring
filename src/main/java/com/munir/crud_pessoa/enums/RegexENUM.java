package com.munir.crud_pessoa.enums;

import lombok.Getter;

@Getter
public enum RegexENUM {
	
	CHARS_ONLY("^[\\p{L} ]+$"),
	NUMBERS_ONLY("^\\d+$"),
	EMAIL_PATTERN("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

	private String value;

	RegexENUM(String value) {

		this.value = value;
	}
}