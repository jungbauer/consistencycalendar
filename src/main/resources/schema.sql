-- USERS is a test and tutorial table.
CREATE TABLE users
(
    id      INT AUTO_INCREMENT PRIMARY KEY,
    name    TEXT NOT NULL,
    age     INT,
    active  BOOLEAN,
    birth_date   DATE
);

-- really don't like that ARRAYs are not typed in H2
CREATE TABLE habit
(
    id  INT AUTO_INCREMENT PRIMARY KEY,
    type    TEXT,
    title   TEXT,
    start_date  DATE,
    end_date    DATE,
    note    TEXT,
    completions ARRAY
);