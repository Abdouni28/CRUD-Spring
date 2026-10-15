package com.munir.crud_pessoa.publishers;

public interface Publisher<T> {
	
	void publish(T mensagem);
}