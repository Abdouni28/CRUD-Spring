package com.munir.crud_pessoa.validadores;

import java.util.List;

import org.springframework.stereotype.Component;

import com.munir.crud_pessoa.dtos.request.DocumentValidationRequestDTO;
import com.munir.crud_pessoa.exceptions.PersonValidationException;
import com.munir.crud_pessoa.utils.MessagesLoader;

@Component
public class DocumentValidator implements Validator<DocumentValidationRequestDTO> {
	
	private final String HIFEN = "-";
	private final String PONTO = ".";
	private final String BLANK = "";
	
	@Override
	public void validate(DocumentValidationRequestDTO requestDTO) {
		
		switch(requestDTO.documentType()) {
		
			case CPF -> validateCPF(requestDTO.document());
		}
	}
	
	private void validateCPF(String document) {
		
		String cpf = document.replace(PONTO, BLANK);
		cpf = cpf.replace(HIFEN, BLANK);
		
		int firstVerifierDigit = Integer.parseInt(cpf.substring(9, 10));
		int secondVerifierDigit = Integer.parseInt(cpf.substring(10));
		
		int correctFirstVerifierDigit = calculateVerifierDigit(cpf, 1);
		int correctSecondVerifierDigit = calculateVerifierDigit(cpf, 2);
		
		if((firstVerifierDigit != correctFirstVerifierDigit) || (secondVerifierDigit != correctSecondVerifierDigit))
			throw new PersonValidationException(MessagesLoader.loadMessage("message.cpf_invalido"));
	}
	
	private Integer calculateVerifierDigit(String cpf, Integer whichVerifier) {
		
		int sum = 0;
		int remainder = 0;
		int multiplier;
		
		List<Integer> cpfDigits = cpf.chars()
								   .map(digit -> Character.getNumericValue(digit))
								   .boxed()
								   .toList();
		
		//validação do primeiro dígito verificador
		if(whichVerifier == 1) {
			
			multiplier = 10;

			//remove-se os dois dígitos verificadores, pois se usa apenas até o nono número para validar o primeiro dígito verificador
			for(int i = 0; i < cpfDigits.size() - 2; i++) {
				
				sum += cpfDigits.get(i) * multiplier--;
			}
			
		} else { //validação do segundo dígito verificador
			
			multiplier = 11;

			//remove-se o segundo dígito verificador, pois se usa apenas até o décimo número para validar o segundo dígito verificador
			for(int i = 0; i < cpfDigits.size() - 1; i++) {
				
				sum += cpfDigits.get(i) * multiplier--;
			}
			
		}
		
		remainder = sum % 11;
		
		return (remainder < 2) ? 0 : 11 - remainder;
	}
}
