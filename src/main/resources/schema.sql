-- USERS is a test and tutorial table.
CREATE TABLE users
(
    id      INT AUTO_INCREMENT PRIMARY KEY,
    name    TEXT NOT NULL,
    age     INT,
    active  BOOLEAN,
    birth_date   DATE
);

CREATE TABLE habit
(
    id  INT AUTO_INCREMENT PRIMARY KEY,
    type    TEXT,
    title   TEXT,
    start_date  DATE,
    end_date    DATE,
    note    TEXT
);

CREATE TABLE completions
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    date DATE,
    habit_id int,
    FOREIGN KEY (habit_id) REFERENCES habit(id)
);