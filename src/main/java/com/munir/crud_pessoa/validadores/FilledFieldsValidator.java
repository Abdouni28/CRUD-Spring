package com.munir.crud_pessoa.validadores;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.munir.crud_pessoa.exceptions.PersonValidationException;
import com.munir.crud_pessoa.utils.MessagesLoader;
import com.munir.crud_pessoa.utils.ReflexaoUtils;

@Component
public class FilledFieldsValidator<T> implements Validator<T> {

	public void validate(T entity) {
		
	    List<Field> fields = Arrays.asList(entity.getClass().getDeclaredFields())
	    					 .stream()
	    					 //exclui todos os campos de JOIN da busca, para validar apenas o campos nativos da entidade
	    					 .filter(campo -> ReflexaoUtils.isNativeField(campo))
	    					 .toList();
		
		for(Field field : fields) {
			
			try {
										
				field.setAccessible(true);
				
				var valorCampo = field.get(entity);
				
				if(valorCampo == null || (field.getType() == String.class && valorCampo.toString().isBlank()))
					throw new PersonValidationException(MessageFormat.format(MessagesLoader.loadMessage("message.todos_campos_obrigatorios"),
														entity.getClass().getSimpleName().toLowerCase()));
				
			} catch (IllegalAccessException e) {

				e.printStackTrace();
			}
		};
	}
}