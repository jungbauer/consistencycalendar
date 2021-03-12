DROP TABLE IF EXISTS billionaires;

CREATE TABLE billionaires
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(250) NOT NULL,
    last_name  VARCHAR(250) NOT NULL,
    career     VARCHAR(250) DEFAULT NULL
);

CREATE TABLE teststuff
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(250) NOT NULL
);

CREATE TABLE users
(
    id      INT AUTO_INCREMENT PRIMARY KEY,
    name    TEXT NOT NULL,
    age     INT,
    active  BOOLEAN,
    birth_date   DATE
);