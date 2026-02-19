--liquibase formatted sql
--changeset munir:create_table_pessoa_aud

CREATE TABLE pessoa_aud (
	rev BIGINT NOT NULL,
	revtype TINYINT NOT NULL,
  	id INT NOT NULL,
	nome VARCHAR(255),
	cpf VARCHAR (11),
	email VARCHAR(255),
	data_nascimento DATE,
	ativa TINYINT(1),
  PRIMARY KEY (rev, id),
  CONSTRAINT fk_rev_pessoa_aud FOREIGN KEY (rev) REFERENCES revinfo (id)
);

--rollback DROP TABLE pessoa_aud