package com.munir.crud_pessoa.dtos.response;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PersonResponseDTO(Long id, String name, String cpf, String email,
								@JsonProperty("birth_date") LocalDate birthDate, Boolean active,
								List<AddressResponseDTO> addresses, List<TelephoneResponseDTO> telephones,
								UserResponseDTO user) implements Serializable {
}