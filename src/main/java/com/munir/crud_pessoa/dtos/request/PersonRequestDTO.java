package com.munir.crud_pessoa.dtos.request;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PersonRequestDTO(Long id, String name, String cpf, String email,
							   @JsonProperty("birth_date") LocalDate birthDate, Boolean active,
							   List<AddressRequestDTO> addresses, List<TelephoneRequestDTO> telephones,
							   UserRequestDTO user) implements Serializable {
}