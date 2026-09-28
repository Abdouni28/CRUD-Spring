--liquibase formatted sql
--changeset munir:create_table_usuario_perfil_aud

CREATE TABLE usuario_perfil_aud(
    id_usuario BIGINT,
    id_perfil TINYINT,
    REV INT NOT NULL,
    REVTYPE TINYINT NOT NULL,
    PRIMARY KEY (id_usuario, id_perfil, REV),
    CONSTRAINT fk_usuario_usuario_perfil_aud FOREIGN KEY (id_usuario) REFERENCES usuario (id),
    CONSTRAINT fk_perfil_usuario_perfil_aud FOREIGN KEY (id_perfil) REFERENCES perfil (id),
	CONSTRAINT fk_usuario_perfil_aud_revinfo FOREIGN KEY (REV) REFERENCES revinfo (REV)
);

--rollback DROP TABLE usuario_perfil_aud