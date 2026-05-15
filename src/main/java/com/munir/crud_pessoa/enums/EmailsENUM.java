package com.munir.crud_pessoa.enums;

import com.munir.crud_pessoa.utils.ResourcesLoader;

import lombok.Getter;

@Getter
public enum EmailsENUM {
	
	NOVA_PESSOA_CADASTRADA("Bem vindo ao nosso sistema!", "emails/email_nova_pessoa_cadastrada.html");

	private final String assunto;
	
	private final String arquivoEmail;

	EmailsENUM(String assunto, String arquivoEmail) {

		this.assunto = assunto;
		this.arquivoEmail = arquivoEmail;
	}

	public String getCorpoEmail() {
	    return ResourcesLoader.loadResourceAsString(arquivoEmail);
	}
}