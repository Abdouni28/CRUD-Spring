package com.munir.crud_pessoa.dtos.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequestDTO implements Serializable {

	private static final long serialVersionUID = -7025107975112277745L;

	private List<String> destinatarios = new ArrayList<>();
	
	private String assunto;
	
	private String corpo;
}
