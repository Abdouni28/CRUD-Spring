package com.munir.crud_pessoa.enums;

import org.hibernate.envers.RevisionType;

import lombok.Getter;

@Getter
public enum AuditOperationTypeENUM {

    INSERTION("INSERTION"),
    MODIFICATION("MODIFICATION"),
    EXCLUSION("EXCLUSION");

    private final String description;

    AuditOperationTypeENUM(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static AuditOperationTypeENUM fromRevisionType(RevisionType revisionType) {
    	
        return switch (revisionType) {
            case ADD -> INSERTION;
            case MOD -> MODIFICATION;
            case DEL -> EXCLUSION;
        };
    }
}