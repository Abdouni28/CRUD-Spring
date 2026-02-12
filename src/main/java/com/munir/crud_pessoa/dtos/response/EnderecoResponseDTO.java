package com.munir.crud_pessoa.dtos.response;

import java.io.Serializable;

public record EnderecoResponseDTO(Long id, String logradouro, String nomeLogradouro, String numero, String bairro,
								  String cidade, String estado, String cep) implements Serializable {
}