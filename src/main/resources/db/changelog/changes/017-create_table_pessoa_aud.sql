--liquibase formatted sql
--changeset munir:create_table_pessoa_aud

CREATE TABLE pessoa_aud(
	id INT NOT NULL AUTO_INCREMENT,
    REV INT NOT NULL,
    REVTYPE TINYINT NOT NULL,
	nome VARCHAR(255),
	cpf VARCHAR (11),
	email VARCHAR(255),
	data_nascimento DATE,
	ativa TINYINT(1) DEFAULT 1,
	PRIMARY KEY(id, REV),
	CONSTRAINT fk_pessoa_aud_revinfo FOREIGN KEY (REV) REFERENCES revinfo (REV)
);

--rollback DROP TABLE pessoa_aud