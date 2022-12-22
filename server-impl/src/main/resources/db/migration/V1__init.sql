CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE balance
(
    id     BIGINT NOT NULL,
    amount BIGINT,
    CONSTRAINT pk_balance PRIMARY KEY (id)
);