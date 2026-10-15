package com.munir.crud_pessoa.email_templates;

import java.util.List;

import com.munir.crud_pessoa.dtos.request.EmailRequestDTO;

public abstract class EmailTemplate {
	
	public EmailRequestDTO buildEmailTemplate() {
		
		List<String> addressees = getAddressees();
		String subject = getSubject();
		String content = getContent();
		
		EmailRequestDTO emailRequestDTO = new EmailRequestDTO(addressees, subject, content);
		
		return emailRequestDTO;
	}

	protected abstract List<String> getAddressees();
	
	protected abstract String getSubject();
	
	protected abstract String getContent();
	
	protected String fillSubjectParameters(String subject) {
		return subject;
	}
	
	protected String fillContentParameters(String content) {
		return content;
	}
}