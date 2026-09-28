--liquibase formatted sql
--changeset munir:create_table_revinfo

CREATE TABLE revinfo (
    REV INT NOT NULL AUTO_INCREMENT,
    REVTSTMP BIGINT NOT NULL,
    AUTOR VARCHAR(100),
    ENDERECO_IP VARCHAR(45),
    PRIMARY KEY (REV)
);

--rollback DROP TABLE revinfo