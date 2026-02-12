--liquibase formatted sql
--changeset munir:create_table_endereco

CREATE TABLE endereco(
	id INT NOT NULL AUTO_INCREMENT UNIQUE,
	id_pessoa INT NOT NULL,
	logradouro VARCHAR(20) NOT NULL,
	nome_logradouro VARCHAR (100) NOT NULL,
	numero VARCHAR(5) NOT NULL,
	bairro VARCHAR(255) NOT NULL,
	cidade VARCHAR(255) NOT NULL,
	estado VARCHAR(2) NOT NULL,
	cep VARCHAR(8) NOT NULL,
	PRIMARY KEY(id),
	CONSTRAINT fk_pessoa_endereco FOREIGN KEY (id_pessoa) REFERENCES pessoa (id)
);


--rollback DROP TABLE endereco