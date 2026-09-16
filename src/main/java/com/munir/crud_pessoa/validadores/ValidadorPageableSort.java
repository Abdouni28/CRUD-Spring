package com.munir.crud_pessoa.validadores;

import java.text.MessageFormat;

import org.springframework.stereotype.Component;

import com.munir.crud_pessoa.dtos.request.ValidacaoPageableSortRequestDTO;
import com.munir.crud_pessoa.exceptions.PessoaValidationException;
import com.munir.crud_pessoa.utils.MessagesLoader;

@Component
public class ValidadorPageableSort implements Validador<ValidacaoPageableSortRequestDTO> {

	@Override
	public void validar(ValidacaoPageableSortRequestDTO requestDTO) {
		
		requestDTO.pageable().getSort().forEach(sort -> {
			
			if (!requestDTO.sortProperties().contains(sort.getProperty())) {
				throw new PessoaValidationException(MessageFormat.format(MessagesLoader.loadMessage("message.campo_ordenacao_invalido"),
													sort.getProperty()));
			}
		});
	}
}
