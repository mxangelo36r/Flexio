SELECT * FROM users;
SELECT * FROM split;
SELECT * FROM weekly_program_split;
SELECT * FROM weekly_program;
SELECT * FROM day_workout_weekly_program;
SELECT * FROM day_workout;
SELECT * FROM day_workout_exercise;
SELECT * FROM exercise;

-- Finding types of workouts in 'Week A'
SELECT dw.`day`, dw.`name` FROM weekly_program wp
INNER JOIN day_workout_weekly_program dwwp
ON wp.weekly_program_id = dwwp.weekly_program_id
INNER JOIN day_workout dw
ON dw.day_workout_id = dwwp.day_workout_id
WHERE wp.weekly_program_id = 1;

-- Finding types of workouts done in a particular day (2024-09-09)

SELECT e.name_exercise, e.weight, e.sets, e.reps FROM exercise e
INNER JOIN day_workout_exercise dwe
ON e.exercise_id = dwe.exercise_id
INNER JOIN day_workout dw
ON dw.day_workout_id = dwe.day_workout_id
WHERE dw.`day` = '2024-09-09';

-- Finding UserGoals

SELECT user_goal_id, user_id, goal_type, target_weight, weekly_visits, start_date, end_date 
FROM user_goals
WHERE user_id = 1;

-- Updating User Goals

UPDATE user_goals SET goal_type = 'WEIGHT_LOSS', target_weight = 60.00, weekly_visits = 7, start_date = '2025-01-01', end_date = '2025-12-01' WHERE user_goal_id = 1;

SELECT * FROM user_goals;
SELECT * FROM users;