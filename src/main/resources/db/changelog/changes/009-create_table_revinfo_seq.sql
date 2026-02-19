--liquibase formatted sql
--changeset munir:create_table_revinfo_seq

CREATE TABLE revinfo_seq(
  next_val BIGINT NOT NULL,
  PRIMARY KEY (next_val)
);

--rollback DROP TABLE revinfo_seq