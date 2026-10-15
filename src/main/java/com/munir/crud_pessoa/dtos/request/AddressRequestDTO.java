package com.munir.crud_pessoa.dtos.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AddressRequestDTO(Long id, String type, String address, String number,
								String neighborhood, String city, String state, String cep,
		  						@JsonProperty("person_id") Long personId) implements Serializable {
}