--liquibase formatted sql
--changeset munir:insert_value_usuario_perfil_admin

INSERT INTO usuario_perfil VALUES (1, 1);

--rollback DELETE FROM usuario_perfil WHERE id_usuario = 1 AND id_perfil = 1