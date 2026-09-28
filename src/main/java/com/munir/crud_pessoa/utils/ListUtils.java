package com.munir.crud_pessoa.utils;

import java.util.List;

public final class ListUtils {
	
	private ListUtils() {}

	public static Boolean isNullOrEmpty(List<?> lista) {
		
		return lista == null || lista.isEmpty();
	}
}
