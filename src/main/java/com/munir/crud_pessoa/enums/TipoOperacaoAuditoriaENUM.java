package com.munir.crud_pessoa.enums;

import org.hibernate.envers.RevisionType;

import lombok.Getter;

@Getter
public enum TipoOperacaoAuditoriaENUM {

    INCLUSAO("INCLUSÃO"),
    ALTERACAO("ALTERAÇÃO"),
    EXCLUSAO("EXCLUSÃO");

    private final String descricao;

    TipoOperacaoAuditoriaENUM(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoOperacaoAuditoriaENUM fromRevisionType(RevisionType revisionType) {
    	
        return switch (revisionType) {
            case ADD -> INCLUSAO;
            case MOD -> ALTERACAO;
            case DEL -> EXCLUSAO;
        };
    }
}
