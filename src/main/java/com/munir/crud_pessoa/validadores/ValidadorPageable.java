package com.munir.crud_pessoa.validadores;

import java.text.MessageFormat;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.munir.crud_pessoa.exceptions.PessoaValidationException;
import com.munir.crud_pessoa.utils.MessagesLoader;

@Component
public class ValidadorPageable {
	
	@Autowired
	private MessagesLoader messagesLoader;
	
	public void validarSortProperties(Pageable pageable, Set<String> sortProperties) {
		
		pageable.getSort().forEach(order -> {
			
			if (!sortProperties.contains(order.getProperty())) {
				throw new PessoaValidationException(MessageFormat.format(messagesLoader.loadMessage("message.campo_ordenacao_invalido"),
													order.getProperty()));
			}
		});
	}

}
