package com.munir.crud_pessoa.dtos.request;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuditoriaRequestDTO(@JsonProperty("id_entidade") Long idEntidade, @JsonProperty("data_inicio") LocalDateTime dataInicio,
								  @JsonProperty("data_fim") LocalDateTime dataFim) implements Serializable {

}
