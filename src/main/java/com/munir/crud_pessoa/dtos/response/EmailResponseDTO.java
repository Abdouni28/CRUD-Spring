package com.munir.crud_pessoa.dtos.response;

import java.io.Serializable;
import java.util.List;

public record EmailResponseDTO(List<String> destinatarios, String assunto, String corpo) implements Serializable {}