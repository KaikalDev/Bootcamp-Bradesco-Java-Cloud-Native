--liquibase formatted sql
--changeset junior:202408191983
--comment: bords table create

create TABLE BOARDS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
) ENGINE=InnoDB

--rollback DROP TABLE BOARDS
