package com.munir.crud_pessoa.dtos.response;

import java.io.Serializable;
import java.util.Date;

public record TokenResponseDTO(String token, Date issuedAt, Date expiration) implements Serializable {
}