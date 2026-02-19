--liquibase formatted sql
--changeset munir:insert_first_value_revinfo_seq

INSERT INTO revinfo_seq VALUES (1);

--rollback DROP TABLE revinfo_seq