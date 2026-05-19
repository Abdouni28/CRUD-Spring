package com.munir.crud_pessoa.validadores;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Pageable;

import com.munir.crud_pessoa.enums.TipoDocumentoENUM;
import com.munir.crud_pessoa.exceptions.PessoaValidationException;
import com.munir.crud_pessoa.utils.MessagesLoader;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

public abstract class Validador<T> {

	protected final String EMPTY_SPACE = " ";
	protected final String BLANK = "";
	protected final String PARENTESIS_ESQUERDO = "(";
	protected final String PARENTESIS_DIREITO = ")";
	protected final String HIFEN = "-";
	protected final String PONTO = ".";

	public abstract void validar(T entity);
	
	public void validarSortProperties(Pageable pageable, Set<String> sortProperties) {
		
		pageable.getSort().forEach(sort -> {
			
			if (!sortProperties.contains(sort.getProperty())) {
				throw new PessoaValidationException(MessageFormat.format(MessagesLoader.loadMessage("message.campo_ordenacao_invalido"),
													sort.getProperty()));
			}
		});
	}
	
	protected <T1> void validarTodosCamposPreenchidos(T1 entity) {
		
	    List<Field> campos = Arrays.asList(entity.getClass().getDeclaredFields())
	    					 .stream()
	    					 //exclui todos os campos de JOIN da busca, para validar apenas o campos nativos da entidade
	    					 .filter(campo -> !campo.isAnnotationPresent(Id.class) &&
	    							 		  !campo.isAnnotationPresent(OneToMany.class) &&
	    							 		  !campo.isAnnotationPresent(ManyToOne.class) &&
	    							 		  !campo.isAnnotationPresent(ManyToMany.class) &&
	    							 		  !campo.isAnnotationPresent(JoinColumn.class) &&
	    							 		  !campo.getName().equals("serialVersionUID"))
	    					 .toList();
		
		for(Field campo : campos) {
			
			try {
										
				campo.setAccessible(true);
				
				var valorCampo = campo.get(entity);
				
				if(valorCampo == null || (campo.getType() == String.class && valorCampo.toString().isBlank()))
					throw new PessoaValidationException(MessageFormat.format(MessagesLoader.loadMessage("message.todos_campos_obrigatorios"),
														entity.getClass().getSimpleName().toLowerCase()));
				
			} catch (IllegalAccessException e) {

				e.printStackTrace();
			}
		};
	}
	
	protected void validarDocumento(String documento, TipoDocumentoENUM tipoDocumento) {
		
		switch(tipoDocumento) {
		
			case CPF -> validarCpf(documento);
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
