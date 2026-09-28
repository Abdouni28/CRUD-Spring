--liquibase formatted sql
--changeset munir:create_table_endereco_aud

CREATE TABLE endereco_aud(
	id INT NOT NULL AUTO_INCREMENT,
    REV INT NOT NULL,
    REVTYPE TINYINT NOT NULL,
	id_pessoa INT NOT NULL,
	logradouro VARCHAR(20) NOT NULL,
	nome_logradouro VARCHAR (100) NOT NULL,
	numero VARCHAR(5) NOT NULL,
	bairro VARCHAR(255) NOT NULL,
	cidade VARCHAR(255) NOT NULL,
	estado VARCHAR(2) NOT NULL,
	cep VARCHAR(8) NOT NULL,
	PRIMARY KEY(id, REV),
	CONSTRAINT fk_pessoa_endereco_aud FOREIGN KEY (id_pessoa) REFERENCES pessoa (id),
	CONSTRAINT fk_endereco_aud_revinfo FOREIGN KEY (REV) REFERENCES revinfo (REV)
);

--rollback DROP TABLE endereco_aud