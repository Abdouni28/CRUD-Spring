--liquibase formatted sql
--changeset munir:create_index_usuario

CREATE INDEX idx_nome_usuario ON usuario (nome_usuario);

--rollback DROP INDEX idx_nome_usuario ON usuario