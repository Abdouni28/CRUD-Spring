package com.munir.crud_pessoa.dtos.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TelephoneRequestDTO(Long id, String number, @JsonProperty("telephone_type") TelephoneTypeRequestDTO telephoneType,
								 @JsonProperty("person_id") Long personId) implements Serializable {
}