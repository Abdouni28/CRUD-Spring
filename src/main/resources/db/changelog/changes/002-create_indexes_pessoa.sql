--liquibase formatted sql
--changeset munir:create_indexes_pessoa

CREATE INDEX idx_cpf ON pessoa (cpf);
CREATE INDEX idx_email ON pessoa (email);

--rollback DROP INDEX idx_cpf ON pessoa
--rollback DROP INDEX idx_email ON pessoa