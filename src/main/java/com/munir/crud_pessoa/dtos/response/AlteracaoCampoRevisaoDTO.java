package com.munir.crud_pessoa.dtos.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AlteracaoCampoRevisaoDTO(@JsonProperty("tipo_entidade") String tipoEntidade,
									   @JsonProperty("id_entidade") Long idEntidade,
									   @JsonProperty("nome_campo") String nomeCampo,
									   @JsonProperty("valor_antigo") String valorAntigo,
									   @JsonProperty("valor_novo") String valorNovo) implements Serializable {

}
