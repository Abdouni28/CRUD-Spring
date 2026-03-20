--liquibase formatted sql
--changeset munir:insert_value_usuario_admin

INSERT INTO usuario VALUES (null, null, 'admin', '$2a$12$4NpHxpok3e5jizOrg43dCOP/fLHyYV2BXkbntnYu8f5fPqeIL5/XS', NOW(), 1);

--rollback DELETE FROM usuario WHERE id = 1