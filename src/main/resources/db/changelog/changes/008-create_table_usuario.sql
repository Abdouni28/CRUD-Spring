--liquibase formatted sql
--changeset munir:create_table_usuario

CREATE TABLE usuario(
	id BIGINT NOT NULL AUTO_INCREMENT UNIQUE,
	id_pessoa INT,
	nome_usuario VARCHAR(50) NOT NULL,
	senha VARCHAR(500) NOT NULL,
	data_criacao TIMESTAMP NOT NULL,
	ativo TINYINT(1) NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT fk_pessoa_usuario FOREIGN KEY (id_pessoa) REFERENCES pessoa (id)
);

--rollback DROP TABLE usuario