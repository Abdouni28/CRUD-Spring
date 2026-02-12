package com.munir.crud_pessoa.dtos.request;

import java.io.Serializable;

public record EnderecoRequestDTO(Long id, String logradouro, String nomeLogradouro, String numero, String bairro,
		  						 String cidade, String estado, String cep) implements Serializable {
}