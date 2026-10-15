package com.munir.crud_pessoa.dtos.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserResponseDTO(Long id, String username, @JsonProperty("creation_date") LocalDateTime creationDate,
							  Boolean active, Set<ProfileResponseDTO> profiles, @JsonProperty("person_id") Long personId) implements Serializable {
}