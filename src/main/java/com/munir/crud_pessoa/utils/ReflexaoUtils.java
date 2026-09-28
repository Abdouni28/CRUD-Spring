package com.munir.crud_pessoa.utils;
  
import java.lang.reflect.Field;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

public final class ReflexaoUtils {

    private ReflexaoUtils() {}

    public static Boolean isNativeField(Field field) {

        return !field.isAnnotationPresent(Id.class) &&
		 	   !field.isAnnotationPresent(OneToMany.class) &&
	 		   !field.isAnnotationPresent(ManyToOne.class) &&
	 		   !field.isAnnotationPresent(ManyToMany.class) &&
	 		   !field.isAnnotationPresent(JoinColumn.class) &&
	 		   !field.getName().equals("serialVersionUID");
    }
}