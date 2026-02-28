package com.munir.crud_pessoa.enums;

import lombok.Getter;

@Getter
public enum EmailsENUM {
	
	NOVA_PESSOA_CADASTRADA("Bem vindo ao nosso sistema!", "emails/email_nova_pessoa_cadastrada.html", Boolean.TRUE);

	private String assunto;
	
	private String arquivoEmail;
	
	private Boolean possuiParametros;

	EmailsENUM(String assunto, String arquivoEmail, Boolean possuiParametros) {

		this.assunto = assunto;
		this.arquivoEmail = arquivoEmail;
		this.possuiParametros = possuiParametros;
	}
}
