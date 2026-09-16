package com.munir.crud_pessoa.validadores;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.munir.crud_pessoa.exceptions.PessoaValidationException;
import com.munir.crud_pessoa.utils.MessagesLoader;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Component
public class ValidadorCamposPreenchidosEntidades<T> implements Validador<T> {

	public void validar(T entity) {
		
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
}
