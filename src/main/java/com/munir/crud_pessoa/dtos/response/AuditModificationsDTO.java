package com.munir.crud_pessoa.dtos.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuditModificationsDTO(@JsonProperty("field_name") String fieldName,
								    @JsonProperty("old_value") String oldValue,
								    @JsonProperty("new_value") String newValue) implements Serializable {

}
