package com.munir.crud_pessoa.dtos.request;

import java.io.Serializable;

public record AuthRequestDTO(String usuario, String senha) implements Serializable {

}
