package com.munir.crud_pessoa.dtos.request;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UsuarioRequestDTO(Long id, @JsonProperty("nome") String nomeUsuario, String senha,
								@JsonProperty("data_criacao") LocalDateTime dataCriacao, Boolean ativo,
								Set<PerfilRequestDTO> perfis) implements Serializable {
}