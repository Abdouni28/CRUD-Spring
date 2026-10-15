package com.munir.crud_pessoa.validadores;

import java.text.MessageFormat;

import org.springframework.stereotype.Component;

import com.munir.crud_pessoa.dtos.request.PageableSortValidationRequestDTO;
import com.munir.crud_pessoa.exceptions.PersonValidationException;
import com.munir.crud_pessoa.utils.MessagesLoader;

@Component
public class PageableSortValidator implements Validator<PageableSortValidationRequestDTO> {

	@Override
	public void validate(PageableSortValidationRequestDTO requestDTO) {
		
		requestDTO.pageable().getSort().forEach(sort -> {
			
			if (!requestDTO.sortProperties().contains(sort.getProperty())) {
				throw new PersonValidationException(MessageFormat.format(MessagesLoader.loadMessage("message.campo_ordenacao_invalido"),
													sort.getProperty()));
			}
		});
	}
}