package com.munir.crud_pessoa.dtos.response;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenResponseDTO(String token, @JsonProperty("issued_at") Date issuedAt, Date expiration) implements Serializable {
}