--liquibase formatted sql
--changeset munir:create_table_usuario_perfil

CREATE TABLE usuario_perfil (
    id_usuario BIGINT,
    id_perfil TINYINT,
    CONSTRAINT fk_usuario_usuario_perfil FOREIGN KEY (id_usuario) REFERENCES usuario (id),
    CONSTRAINT fk_perfil_usuario_perfil FOREIGN KEY (id_perfil) REFERENCES perfil (id)
);

--rollback DROP TABLE usuario_perfil