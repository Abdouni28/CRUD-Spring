package com.munir.crud_pessoa.dtos.request;

import java.io.Serializable;

public record TipoTelefoneRequestDTO(Long id, String descricao) implements Serializable {
}