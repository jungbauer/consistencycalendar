-- new column
ALTER TABLE HABIT ADD created_date DATE;

-- new test data
UPDATE HABIT SET HABIT.created_date = '2021-01-30' WHERE ID = 1 OR ID = 2;