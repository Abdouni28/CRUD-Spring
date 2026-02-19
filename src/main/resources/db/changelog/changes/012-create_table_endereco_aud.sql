--liquibase formatted sql
--changeset munir:create_table_endereco_aud

CREATE TABLE endereco_aud(
	rev BIGINT NOT NULL,
	revtype TINYINT NOT NULL,
	id INT NOT NULL,
	id_pessoa INT NOT NULL,
	logradouro VARCHAR(20) NOT NULL,
	nome_logradouro VARCHAR (100) NOT NULL,
	numero VARCHAR(5) NOT NULL,
	bairro VARCHAR(255) NOT NULL,
	cidade VARCHAR(255) NOT NULL,
	estado VARCHAR(2) NOT NULL,
	cep VARCHAR(8) NOT NULL,
	PRIMARY KEY(rev, id),
	CONSTRAINT fk_rev_endereco_aud FOREIGN KEY (rev) REFERENCES revinfo (id)
);

--rollback DROP TABLE endereco_aud