package com.munir.crud_pessoa.dtos.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id_commit", "tipo_entidade", "id_entidade", "tipo_operacao", "data_commit", "endereco_ip", "alteracoes"})
public class AuditoriaResponseDTO implements Serializable {

	private static final long serialVersionUID = -8507954488377750927L;

	@JsonProperty("id_commit")
	private Long idCommit;

	@JsonProperty("tipo_entidade")
	private String tipoEntidade;
	
    @JsonProperty("id_entidade")
    private Long idEntidade;
    
    @JsonProperty("tipo_operacao")
    private String tipoOperacao;
    
    @JsonProperty("data_commit")
    LocalDateTime dataCommit;
    
    @JsonProperty("endereco_ip")
    private String enderecoIp;
    
    List<AlteracaoCampoRevisaoDTO> alteracoes;
}
