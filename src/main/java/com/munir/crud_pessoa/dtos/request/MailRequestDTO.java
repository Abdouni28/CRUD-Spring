package com.munir.crud_pessoa.dtos.request;

import java.io.Serializable;
import java.util.List;

import com.munir.crud_pessoa.enums.EmailsENUM;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MailRequestDTO<T> implements Serializable {

	private static final long serialVersionUID = -7025107975112277745L;

	private List<String> destinatarios;
	
	private EmailsENUM emailENUM;
	
	//Objeto usado para montar os parâmetros do email, caso o emailENUM tenha parâmetros. Ex.: Pessoa, Endereço, etc.
	private T objeto;
}
