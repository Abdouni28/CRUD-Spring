package com.munir.crud_pessoa.dtos.response;

import java.io.Serializable;
import java.time.LocalDateTime;

public record ExceptionResponseDTO(LocalDateTime timestamp, String message, String exceptionName) implements Serializable {}