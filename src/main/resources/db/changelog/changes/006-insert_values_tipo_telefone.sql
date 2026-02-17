--liquibase formatted sql
--changeset munir:insert_values_tipo_telefone

INSERT INTO tipo_telefone VALUES (1, 'CELULAR');
INSERT INTO tipo_telefone VALUES (2, 'FIXO');

--rollback DELETE FROM tipo_teleone WHERE id IN (1, 2)