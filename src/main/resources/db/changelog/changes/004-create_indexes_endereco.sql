--liquibase formatted sql
--changeset munir:create_indexes_endereco

CREATE INDEX idx_cep ON endereco (cep);

--rollback DROP INDEX idx_cep ON endereco