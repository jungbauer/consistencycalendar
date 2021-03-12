-- USERS is a test and tutorial table.
CREATE TABLE users
(
    id      INT AUTO_INCREMENT PRIMARY KEY,
    name    TEXT NOT NULL,
    age     INT,
    active  BOOLEAN,
    birth_date   DATE
);