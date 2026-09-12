package com.munir.crud_pessoa.receivers;

public interface Receiver<T> {
	
	void receber(T mensagem);
}
