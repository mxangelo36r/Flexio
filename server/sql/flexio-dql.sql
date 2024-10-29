SELECT * FROM users;
SELECT * FROM split;
SELECT * FROM weekly_program;
SELECT * FROM day_workout;
SELECT * FROM exercise;

SELECT dw.`day`, dw.`name` FROM weekly_program wp
INNER JOIN day_workout_weekly_program dwwp
ON wp.weekly_program_id = dwwp.weekly_program_id
INNER JOIN day_workout dw
ON dw.day_workout_id = dwwp.day_workout_id
WHERE wp.weekly_program_id = 1;