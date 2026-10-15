package com.munir.crud_pessoa.dtos.response;

import java.io.Serializable;
import java.util.List;

public record EmailResponseDTO(List<String> addressees, String subject, String content) implements Serializable {}