package com.munir.crud_pessoa.dtos.request;

import java.io.Serializable;

public record AuthRequestDTO(String username, String password) implements Serializable {

}
