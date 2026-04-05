package com.munir.crud_pessoa.dtos.request.filtros_busca;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FiltrosBuscaPessoaRequestDTO(
		@JsonProperty("id_pessoa") Long idPessoa,
	    String nome,
	    String cpf,
	    String email,
	    @JsonProperty("data_nascimento_de") LocalDate dataNascimentoDe,
	    @JsonProperty("data_nascimento_ate") LocalDate dataNascimentoAte,
	    Boolean ativa,
	    @JsonProperty("id_endereco") Long idEndereco,
	    String logradouro,
	    @JsonProperty("nome_logradouro") String nomeLogradouro,
	    @JsonProperty("numero_endereco") String numeroEndereco,
	    String bairro,
	    String cidade,
	    String estado,
	    String cep,
	    @JsonProperty("id_telefone") Long idTelefone,
	    @JsonProperty("numero_telefone") String numeroTelefone,
	    @JsonProperty("id_tipo_telefone") Long idTipoTelefone,
	    @JsonProperty("descricao_tipo_telefone") String descricaoTipoTelefone) implements Serializable {
}