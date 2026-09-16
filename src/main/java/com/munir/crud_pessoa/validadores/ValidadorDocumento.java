package com.munir.crud_pessoa.validadores;

import java.util.List;

import org.springframework.stereotype.Component;

import com.munir.crud_pessoa.dtos.request.ValidacaoDocumentoRequestDTO;
import com.munir.crud_pessoa.exceptions.PessoaValidationException;
import com.munir.crud_pessoa.utils.MessagesLoader;

@Component
public class ValidadorDocumento implements Validador<ValidacaoDocumentoRequestDTO> {
	
	private final String HIFEN = "-";
	private final String PONTO = ".";
	private final String BLANK = "";
	
	@Override
	public void validar(ValidacaoDocumentoRequestDTO requestDTO) {
		
		switch(requestDTO.tipoDocumento()) {
		
			case CPF -> validarCpf(requestDTO.documento());
		}
	}
	
	private void validarCpf(String documento) {
		
		documento = documento.replace(PONTO, BLANK);
		documento = documento.replace(HIFEN, BLANK);
		
		int digitoVerificador1 = Integer.parseInt(documento.substring(9, 10));
		int digitoVerificador2 = Integer.parseInt(documento.substring(10));
		
		int digitoVerificadorCorreto1 = calcularDigitoVerificadorCpfCorreto(documento, 1);
		int digitoVerificadorCorreto2 = calcularDigitoVerificadorCpfCorreto(documento, 2);
		
		if((digitoVerificador1 != digitoVerificadorCorreto1) || (digitoVerificador2 != digitoVerificadorCorreto2))
			throw new PessoaValidationException(MessagesLoader.loadMessage("message.cpf_invalido"));
	}
	
	private Integer calcularDigitoVerificadorCpfCorreto(String cpf, Integer qualVerificador) {
		
		int soma = 0;
		int resto = 0;
		int multiplicador;
		
		List<Integer> numerosCpf = cpf.chars()
								   .map(numero -> Character.getNumericValue(numero))
								   .boxed()
								   .toList();
		
		//validação do primeiro dígito verificador
		if(qualVerificador == 1) {
			
			multiplicador = 10;

			//remove-se os dois dígitos verificadores, pois se usa apenas até o nono número para validar o primeiro dígito verificador
			for(int i = 0; i < numerosCpf.size() - 2; i++) {
				
				soma += numerosCpf.get(i) * multiplicador--;
			}
			
		} else { //validação do segundo dígito verificador
			
			multiplicador = 11;

			//remove-se o segundo dígito verificador, pois se usa apenas até o décimo número para validar o segundo dígito verificador
			for(int i = 0; i < numerosCpf.size() - 1; i++) {
				
				soma += numerosCpf.get(i) * multiplicador--;
			}
			
		}
		
		resto = soma % 11;
		
		return (resto < 2) ? 0 : 11 - resto;
	}
}
