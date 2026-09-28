--liquibase formatted sql
--changeset munir:create_table_usuario_aud

CREATE TABLE usuario_aud(
	id BIGINT NOT NULL AUTO_INCREMENT,
    REV INT NOT NULL,
    REVTYPE TINYINT NOT NULL,
	id_pessoa INT,
	nome_usuario VARCHAR(50) NOT NULL,
	senha VARCHAR(500) NOT NULL,
	data_criacao TIMESTAMP NOT NULL,
	ativo TINYINT(1) NOT NULL,
	PRIMARY KEY (id, REV),
	CONSTRAINT fk_pessoa_usuario_aud FOREIGN KEY (id_pessoa) REFERENCES pessoa (id),
	CONSTRAINT fk_usuario_aud_revinfo FOREIGN KEY (REV) REFERENCES revinfo (REV)
);

--rollback DROP TABLE usuario_aud