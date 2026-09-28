--liquibase formatted sql
--changeset munir:create_table_telefone_aud

CREATE TABLE telefone_aud(
	id INT NOT NULL AUTO_INCREMENT,
    REV INT NOT NULL,
    REVTYPE TINYINT NOT NULL,
	id_tipo_telefone INT NOT NULL,
	id_pessoa INT NOT NULL,
	numero VARCHAR(11) NOT NULL,
	PRIMARY KEY(id, REV),
	CONSTRAINT fk_tipo_telefone_aud FOREIGN KEY (id_tipo_telefone) REFERENCES tipo_telefone (id),
	CONSTRAINT fk_pessoa_telefone_aud FOREIGN KEY (id_pessoa) REFERENCES pessoa (id),
	CONSTRAINT fk_telefone_aud_revinfo FOREIGN KEY (REV) REFERENCES revinfo (REV)
);

--rollback DROP TABLE telefone_aud