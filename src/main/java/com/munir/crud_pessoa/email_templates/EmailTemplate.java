package com.munir.crud_pessoa.email_templates;

import java.util.List;

import com.munir.crud_pessoa.dtos.request.EmailRequestDTO;

public abstract class EmailTemplate {
	
	public EmailRequestDTO montarEmailTemplate() {
		
		List<String> destinatarios = getDestinatarios();
		String assunto = getAssunto();
		String corpo = getCorpo();
		
		EmailRequestDTO emailRequestDTO = new EmailRequestDTO(destinatarios, assunto, corpo);
		
		return emailRequestDTO;
	}

	protected abstract List<String> getDestinatarios();
	
	protected abstract String getAssunto();
	
	protected abstract String getCorpo();
	
	protected String preencherParametrosAssunto(String assunto) {
		return assunto;
	}
	
	protected String preencherParametrosCorpo(String corpo) {
		return corpo;
	}
}
