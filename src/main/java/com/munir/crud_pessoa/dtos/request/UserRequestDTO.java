package com.munir.crud_pessoa.dtos.request;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserRequestDTO(Long id, String username, String password, Boolean active,
							 Set<ProfileRequestDTO> profiles, @JsonProperty("person_id") Long personId) implements Serializable {
}