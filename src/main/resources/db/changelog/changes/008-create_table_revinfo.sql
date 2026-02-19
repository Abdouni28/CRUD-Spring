--liquibase formatted sql
--changeset munir:create_table_revinfo

CREATE TABLE revinfo(
  id BIGINT NOT NULL AUTO_INCREMENT,
  timestamp BIGINT NOT NULL,
  ip_address VARCHAR(15) NOT NULL,
  PRIMARY KEY (id)
);

--rollback DROP TABLE revinfo