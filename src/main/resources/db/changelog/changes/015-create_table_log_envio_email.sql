--liquibase formatted sql
--changeset munir:create_table_log_envio_email

CREATE TABLE log_envio_emails(
	id INT NOT NULL AUTO_INCREMENT UNIQUE,
	destinatarios TEXT NOT NULL,
	assunto VARCHAR(255) NOT NULL,
	corpo TEXT NOT NULL,
	mensagem_erro VARCHAR(255) NOT NULL,
	data_tentativa_envio TIMESTAMP NOT NULL,
	PRIMARY KEY (id)
);

--rollback DELETE TABLE log_envio_emails