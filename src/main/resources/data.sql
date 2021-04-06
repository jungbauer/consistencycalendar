INSERT  INTO users (name, age, active, birth_date) VALUES
('Fred', 14, true, '2006-02-04'),
('George', 36, false, '1985-02-15');

INSERT INTO habit (type, title, start_date, end_date, note) VALUES
 ('YesNoHabit', 'TEST HABIT: Push-ups', '2021-02-01', '2021-04-30', 'Push myself off the ground.', '2021-01-30');

INSERT INTO habit (type, title, start_date, end_date, note) VALUES
('YesNoHabit', 'Second test habit', '2021-03-01', '2021-06-30', 'dump completions', '2021-01-30');

INSERT INTO completions (date, habit_id) VALUES ('2021-02-04', 1);
INSERT INTO completions (date, habit_id) VALUES ('2021-02-05', 1);
INSERT INTO completions (date, habit_id) VALUES ('2021-02-07', 1);
INSERT INTO completions (date, habit_id) VALUES ('2021-02-09', 1);
INSERT INTO completions (date, habit_id) VALUES ('2021-02-10', 1);
INSERT INTO completions (date, habit_id) VALUES ('2021-02-14', 1);
INSERT INTO completions (date, habit_id) VALUES ('2021-02-16', 1);
INSERT INTO completions (date, habit_id) VALUES ('2021-02-18', 1);
INSERT INTO completions (date, habit_id) VALUES ('2021-02-19', 1);
INSERT INTO completions (date, habit_id) VALUES ('2021-02-22', 1);
INSERT INTO completions (date, habit_id) VALUES ('2021-03-01', 1);

INSERT INTO completions (date, habit_id) VALUES ('2021-03-04', 2);
INSERT INTO completions (date, habit_id) VALUES ('2021-04-05', 2);
INSERT INTO completions (date, habit_id) VALUES ('2021-05-07', 2);
INSERT INTO completions (date, habit_id) VALUES ('2021-06-09', 2);
