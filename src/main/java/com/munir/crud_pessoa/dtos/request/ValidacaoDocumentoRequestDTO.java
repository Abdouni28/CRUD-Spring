package com.munir.crud_pessoa.dtos.request;

import java.io.Serializable;

import com.munir.crud_pessoa.enums.TipoDocumentoENUM;

public record ValidacaoDocumentoRequestDTO(String documento, TipoDocumentoENUM tipoDocumento) implements Serializable {

}
