DROP TABLE student1 IF EXISTS;
CREATE TABLE student1
(
    id           VARCHAR(32) NOT NULL,
    name         VARCHAR(50) NOT NULL,
    age          INT         NOT NULL,
    sex          INT         NOT NULL,
    sno          VARCHAR(20) NOT NULL,
    phone        VARCHAR(20) NULL,
    address      VARCHAR(100) NULL,
    card_balance DECIMAL(6, 2) NULL,
    status       INT         NOT NULL,
    version      INT NULL,
    created_at   TIMESTAMP   NOT NULL,
    updated_at   TIMESTAMP NULL,
    disabled_at  TIMESTAMP NULL,
    PRIMARY KEY (id)
);
