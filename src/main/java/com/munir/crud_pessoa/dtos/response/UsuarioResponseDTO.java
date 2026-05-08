package com.munir.crud_pessoa.dtos.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UsuarioResponseDTO(Long id, @JsonProperty("nome") String nomeUsuario, @JsonProperty("data_criacao") LocalDateTime dataCriacao,
								 Boolean ativo, Set<PerfilResponseDTO> perfis) implements Serializable {
}