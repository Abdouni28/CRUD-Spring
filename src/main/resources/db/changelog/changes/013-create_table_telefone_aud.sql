--liquibase formatted sql
--changeset munir:create_table_telefone_aud

CREATE TABLE telefone_aud(
	rev BIGINT NOT NULL,
	revtype TINYINT NOT NULL,
	id INT NOT NULL,
	id_tipo_telefone INT NOT NULL,
	id_pessoa INT NOT NULL,
	numero VARCHAR(11) NOT NULL,
	PRIMARY KEY(rev, id),
	CONSTRAINT fk_rev_telefone_aud FOREIGN KEY (rev) REFERENCES revinfo (id)
);

--rollback DROP TABLE telefone_aud