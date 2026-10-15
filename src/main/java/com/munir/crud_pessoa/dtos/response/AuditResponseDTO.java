package com.munir.crud_pessoa.dtos.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"revision_id", "revision_date", "operation_type", "id_address", "author", "modifications"})
public record AuditResponseDTO(@JsonProperty("revision_id") Long revisionId,
							   @JsonProperty("revision_date") LocalDateTime revisionDate,
							   @JsonProperty("operation_type") String operationType,    
							   @JsonProperty("id_address") String ipAddress,
							   @JsonProperty("author") String author,
							   List<AuditModificationsDTO> modifications) implements Serializable {
	
}