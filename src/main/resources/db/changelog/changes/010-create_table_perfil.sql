--liquibase formatted sql
--changeset munir:create_table_perfil

CREATE TABLE perfil (
    id TINYINT AUTO_INCREMENT,
    nome VARCHAR(100),
    PRIMARY KEY (id)
);

--rollback DROP TABLE perfil