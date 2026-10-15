package com.munir.crud_pessoa.enums;

import com.munir.crud_pessoa.utils.ResourcesLoader;

import lombok.Getter;

@Getter
public enum EmailsENUM {
	
	NEW_PERSON("Bem vindo ao nosso sistema!", "emails/new_person.html");

	private final String subject;
	
	private final String file;

	EmailsENUM(String subject, String file) {

		this.subject = subject;
		this.file = file;
	}

	public String getContent() {
	    return ResourcesLoader.loadResourceAsString(file);
	}
}