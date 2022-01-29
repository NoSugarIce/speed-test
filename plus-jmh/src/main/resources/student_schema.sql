DROP TABLE student IF EXISTS;
CREATE TABLE student
(
    id           VARCHAR(32)   NOT NULL,
    name         VARCHAR(50)   NOT NULL,
    age          INT           NOT NULL,
    sex          INT           NOT NULL,
    sno          VARCHAR(20)   NOT NULL,
    phone        VARCHAR(20)   NULL,
    address      VARCHAR(100)  NULL,
    card_balance DECIMAL(6, 2) NULL,
    status       INT           NOT NULL,
    version      INT           NULL,
    created_at   DATETIME      NOT NULL,
    updated_at   DATETIME      NULL,
    PRIMARY KEY (id)
);
