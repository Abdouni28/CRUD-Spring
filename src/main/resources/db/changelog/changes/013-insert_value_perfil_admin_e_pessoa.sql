--liquibase formatted sql
--changeset munir:insert_value_perfil_admin_e_pessoa

INSERT INTO perfil VALUES (null, 'ADMIN');
INSERT INTO perfil VALUES (null, 'PESSOA');

--rollback DELETE FROM perfil WHERE id IN (1, 2)