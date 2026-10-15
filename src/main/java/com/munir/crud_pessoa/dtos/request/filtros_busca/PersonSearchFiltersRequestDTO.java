package com.munir.crud_pessoa.dtos.request.filtros_busca;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PersonSearchFiltersRequestDTO(
		@JsonProperty("person_id") Long personId,
	    String name,
	    String cpf,
	    String email,
	    @JsonProperty("from_birth_date") LocalDate fromBirthDate,
	    @JsonProperty("to_birth_date") LocalDate toBirthDate,
	    Boolean active,
	    @JsonProperty("address_id") Long addressId,
	    String address,
	    @JsonProperty("address_type") String addressType,
	    @JsonProperty("address_number") String addressNumber,
	    String neighborhood,
	    String city,
	    String state,
	    String cep,
	    @JsonProperty("telephone_id") Long telephoneId,
	    @JsonProperty("telephone_number") String telephoneNumber,
	    @JsonProperty("telephone_type_id") Long telephoneTypeId,
	    @JsonProperty("telephone_type_description") String telephoneTypeDescription) implements Serializable {
}