package com.munir.crud_pessoa.dtos.request;

import java.io.Serializable;
import java.util.List;

public record EmailRequestDTO(List<String> destinatarios, String assunto, String corpo) implements Serializable {}
