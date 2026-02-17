--liquibase formatted sql
--changeset munir:create_table_tipo_telefone

CREATE TABLE tipo_telefone(
	id INT NOT NULL AUTO_INCREMENT UNIQUE,
	descricao VARCHAR(20) NOT NULL,
	PRIMARY KEY(id)
);

--rollback DROP TABLE tipo_telefone