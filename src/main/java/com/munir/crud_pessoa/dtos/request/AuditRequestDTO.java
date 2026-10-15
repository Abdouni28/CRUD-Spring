package com.munir.crud_pessoa.dtos.request;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuditRequestDTO(@JsonProperty("entity_id") Long entityId, @JsonProperty("from_date") LocalDateTime fromDate,
							  @JsonProperty("to_date") LocalDateTime toDate) implements Serializable {
}