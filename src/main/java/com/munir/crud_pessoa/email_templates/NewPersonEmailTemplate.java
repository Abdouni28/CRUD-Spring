package com.munir.crud_pessoa.email_templates;

import java.util.List;

import com.munir.crud_pessoa.dtos.response.PersonResponseDTO;
import com.munir.crud_pessoa.enums.EmailsENUM;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NewPersonEmailTemplate extends EmailTemplate {

	private final PersonResponseDTO person;
	
	@Override
	protected List<String> getAddressees(){
		return List.of(person.email());
	}

	@Override
	protected String getSubject() {
		
		return EmailsENUM.NEW_PERSON.getSubject();
	}

	@Override
	protected String getContent() {

		String content = EmailsENUM.NEW_PERSON.getContent();
		content = fillContentParameters(content);
		
		return content;
	}

	@Override
	protected String fillContentParameters(String content) {
		
		content = content.replace(":name", person.name());
		content = content.replace(":username", person.user().username());
		
		return content;
	}
}