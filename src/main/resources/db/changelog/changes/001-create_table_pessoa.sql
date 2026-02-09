--liquibase formatted sql
--changeset munir:create_table_pessoa

CREATE TABLE pessoa(
	id INT NOT NULL AUTO_INCREMENT UNIQUE,
	nome VARCHAR(255),
	cpf VARCHAR (11),
	email VARCHAR(255),
	data_nascimento DATE,
	ativa TINYINT(1) DEFAULT 1,
	PRIMARY KEY(id)
);

--rollback DROP TABLE pessoa