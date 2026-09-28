package com.munir.crud_pessoa.dtos.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id_revisao", "data_revisao", "id_entidade", "tipo_operacao", "endereco_ip", "autor", "alteracoes"})
public record AuditoriaResponseDTO(@JsonProperty("id_revisao") Long idRevisao,
								   @JsonProperty("data_revisao") LocalDateTime dataRevisao,
								   @JsonProperty("tipo_operacao") String tipoOperacao,    
								   @JsonProperty("endereco_ip") String enderecoIp,
								   @JsonProperty("autor") String autor,
								   List<AlteracaoCampoRevisaoDTO> alteracoes) implements Serializable {
	
}
