package com.munir.crud_pessoa.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum SimNaoEnum {
	
	YES("Yes", "Y"),
	NO("No", "N");
	
	private String value;
	
	private String abbreviation;
}