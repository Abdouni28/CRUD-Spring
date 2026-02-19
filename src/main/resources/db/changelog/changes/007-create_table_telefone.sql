--liquibase formatted sql
--changeset munir:create_table_telefone

CREATE TABLE telefone(
	id INT NOT NULL AUTO_INCREMENT UNIQUE,
	id_tipo_telefone INT NOT NULL,
	id_pessoa INT NOT NULL,
	numero VARCHAR(11) NOT NULL,
	PRIMARY KEY(id),
	CONSTRAINT fk_tipo_telefone FOREIGN KEY (id_tipo_telefone) REFERENCES tipo_telefone (id),
	CONSTRAINT fk_pessoa_telefone FOREIGN KEY (id_pessoa) REFERENCES pessoa (id)
);

--rollback DROP TABLE telefone