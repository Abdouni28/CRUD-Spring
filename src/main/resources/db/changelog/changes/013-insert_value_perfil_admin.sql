--liquibase formatted sql
--changeset munir:insert_value_perfil_admin

INSERT INTO perfil VALUES (null, 'ADMIN');

--rollback DELETE FROM perfil WHERE id = 1