package com.munir.crud_pessoa.emails;

import java.util.List;

import com.munir.crud_pessoa.dtos.request.EmailRequestDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class Email {
	
	private final List<String> destinatarios;
	
	public EmailRequestDTO montarEmailRequestDTO() {
		
		List<String> destinatarios = getDestinatarios();
		String assunto = getAssunto();
		String corpo = getCorpo();
		
		EmailRequestDTO emailRequestDTO = new EmailRequestDTO(destinatarios, assunto, corpo);
		
		return emailRequestDTO;
	}
	
	private List<String> getDestinatarios(){
		
		return this.destinatarios;
	};
	
	protected abstract String getAssunto();
	
	protected abstract String getCorpo();
	
	protected String preencherParametrosAssunto(String assunto) {
		return assunto;
	}
	
	protected String preencherParametrosCorpo(String corpo) {
		return corpo;
	}
}
