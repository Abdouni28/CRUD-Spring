package com.munir.crud_pessoa.dtos.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TelephoneResponseDTO(Long id, String number, @JsonProperty("telephone_type") TelephoneTypeResponseDTO telephoneType,
								  @JsonProperty("person_id") Long personId) implements Serializable {
}