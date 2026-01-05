package com.munir.crud_pessoa.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum SimNaoEnum {
	
	SIM("Sim", "S"),
	NAO("Não", "N");
	
	private String valor;
	
	private String flag;
}