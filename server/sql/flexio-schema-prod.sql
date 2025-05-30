drop database if exists flexio;
create database flexio;
use flexio;

-- USER

CREATE TABLE users (

	user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(250) NOT NULL,
    `password` VARCHAR(200) NOT NULL,
    weight DOUBLE NOT NULL, -- For now weight is in KG 
    height_ft INT NOT NULL,
    height_in INT NOT NULL
);

-- EXERCISE

CREATE TABLE exercise (

	exercise_id INT PRIMARY KEY AUTO_INCREMENT,
	name_exercise VARCHAR(50) NOT NULL,
    weight DOUBLE,
    sets INT NOT NULL,
    reps INT NOT NULL
);

CREATE TABLE day_workout (
	
    day_workout_id INT PRIMARY KEY AUTO_INCREMENT,
    `day` DATE NOT NULL,
    `name` VARCHAR(100)
);

CREATE TABLE day_workout_exercise (

	exercise_id INT,
    day_workout_id INT,
	PRIMARY KEY (exercise_id, day_workout_id),
    FOREIGN KEY (exercise_id) REFERENCES exercise(exercise_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (day_workout_id) REFERENCES day_workout(day_workout_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE weekly_program (

	weekly_program_id INT PRIMARY KEY AUTO_INCREMENT,
    program_name VARCHAR(50),
    category ENUM('Hypertrophy', 'Strength', 'Powerlifting', 'Bodybuilding', 'Functional Fitness')
);


CREATE TABLE day_workout_weekly_program (

	day_workout_id INT,
    weekly_program_id INT,
	PRIMARY KEY (day_workout_id, weekly_program_id),
    FOREIGN KEY (day_workout_id) REFERENCES day_workout(day_workout_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (weekly_program_id) REFERENCES weekly_program(weekly_program_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE split (

	split_id INT PRIMARY KEY AUTO_INCREMENT,
    split_name VARCHAR(50)
);

CREATE TABLE weekly_program_split (

	weekly_program_id INT,
    split_id INT,
	PRIMARY KEY (weekly_program_id, split_id),
    FOREIGN KEY (weekly_program_id) REFERENCES weekly_program(weekly_program_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (split_id) REFERENCES split(split_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- GOALS

CREATE TABLE user_goals (

    user_goal_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    goal_type ENUM('WEIGHT_LOSS', 'BUILD_MUSCLE', 'GET_STRONGER', 'MAINTAIN_WEIGHT') NOT NULL,
    target_weight DECIMAL(10, 2),  -- Target weight for goals related to weight
    weekly_visits INT,             -- Number of visits per week
    start_date DATE,
    end_date DATE,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE daily_goals (
    daily_goal_id INT AUTO_INCREMENT PRIMARY KEY, -- Unique identifier for each daily goal
    user_id INT,                                  -- ID of the user
    day_workout_id INT,                           -- ID of the day workout
    exercise_id INT,                              -- ID of the exercise
    completed BOOLEAN,                            -- Status of goal completion
    UNIQUE KEY unique_user_exercise (user_id, exercise_id), -- Ensures a user has only one entry per exercise
    FOREIGN KEY (exercise_id, day_workout_id) REFERENCES day_workout_exercise(exercise_id, day_workout_id)
);


CREATE TABLE weekly_goals (

    weekly_goal_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    week_start DATE NOT NULL,   -- Start date of the week
    week_end DATE NOT NULL,     -- End date of the week
    gym_visits INT DEFAULT 0,   -- Number of gym visits that week
    all_workouts_completed BOOLEAN DEFAULT FALSE, -- Whether all weekly workouts were completed
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- ** INSERTING MOCK DATA **

INSERT INTO users (user_id, username, email, `password`, weight, height_ft, height_in)
VALUES
    (1, 'angelomrosales', 'm.angelo36r@gmail.com', 'somePassword123', 60, 5, 7);

INSERT INTO split (split_id, split_name)
VALUES
    (1, '12 Week Growth');
    
INSERT INTO weekly_program (weekly_program_id, program_name, category)
VALUES
    (1, 'Week A', 'Hypertrophy'),
    (2, 'Week B', 'Strength');
    
INSERT INTO weekly_program_split (weekly_program_id, split_id)
VALUES
    (1, 1),
    (2, 1);

INSERT INTO day_workout (day_workout_id, `day`, `name`)
VALUES
    (1, '2024-09-09', 'Push - Chest + Shoulders'),
    (2, '2024-09-10', 'Pull - Back'),
    (3, '2024-09-11', 'Legs - Quads'),
    (4, '2024-09-12', 'Push - Triceps'),
    (5, '2024-09-13', 'Pull - Biceps'),
    (6, '2024-09-14', 'Legs - Hamstring'),
    (7, '2024-09-16', 'Chest I'),
    (8, '2024-09-17', 'Back I'),
    (9, '2024-09-18', 'Legs I'),
    (10, '2024-09-20', 'Chest II'),
    (11, '2024-09-21', 'Back II'),
    (12, '2024-09-22', 'Legs II');

INSERT INTO day_workout_weekly_program (day_workout_id, weekly_program_id)
VALUES
    (1, 1),
    (2, 1),
    (3, 1),
    (4, 1),
    (5, 1),
    (6, 1),
    (1, 2),
    (2, 2), 
    (3, 2),
    (4, 2),
    (5, 2),
    (6, 2);

INSERT INTO exercise (exercise_id, name_exercise, weight, sets, reps)
VALUES
    -- Push - Chest + Shoulders
    (1, 'Bench Press', 60, 4, 10),
    (2, 'Incline Dumbbell Press', 25, 3, 12),
    (3, 'Shoulder Press', 40, 3, 10),
    (4, 'Lateral Raise', 10, 4, 15),

    -- Pull - Back
    (5, 'Pull-Up', 50, 4, 8),
    (6, 'Barbell Row', 50, 4, 10),
    (7, 'Lat Pulldown', 45, 3, 10),
    (8, 'Face Pull', 20, 3, 12),

    -- Legs - Quads
    (9, 'Squat', 80, 4, 8),
    (10, 'Leg Press', 150, 4, 10),
    (11, 'Lunges', 20, 3, 12),
    (12, 'Leg Extension', 40, 3, 15),

    -- Push - Triceps
    (13, 'Close Grip Bench Press', 50, 3, 10),
    (14, 'Tricep Extension', 20, 3, 12),
    (15, 'Overhead Tricep Extension', 15, 3, 12),
    (16, 'Dips', NULL, 4, 10),

    -- Pull - Biceps
    (17, 'Barbell Curl', 15, 3, 12),
    (18, 'Hammer Curl', 12, 3, 12),
    (19, 'Preacher Curl', 10, 3, 10),
    (20, 'Cable Curl', 15, 3, 15),

    -- Legs - Hamstring
    (21, 'Romanian Deadlift', 60, 4, 10),
    (22, 'Lying Leg Curl', 35, 3, 12),
    (23, 'Seated Leg Curl', 30, 3, 12),
    (24, 'Good Mornings', 20, 3, 10);
    
INSERT INTO day_workout_exercise (exercise_id, day_workout_id)
VALUES
    -- Push - Chest + Shoulders
    (1, 1), -- Bench Press
    (2, 1), -- Incline Dumbbell Press
    (3, 1), -- Shoulder Press
    (4, 1), -- Lateral Raise

    -- Pull - Back
    (5, 2), -- Pull-Up
    (6, 2), -- Barbell Row
    (7, 2), -- Lat Pulldown
    (8, 2), -- Face Pull

    -- Legs - Quads
    (9, 3), -- Squat
    (10, 3), -- Leg Press
    (11, 3), -- Lunges
    (12, 3), -- Leg Extension

    -- Push - Triceps
    (13, 4), -- Close Grip Bench Press
    (14, 4), -- Tricep Extension
    (15, 4), -- Overhead Tricep Extension
    (16, 4), -- Dips

    -- Pull - Biceps
    (17, 5), -- Barbell Curl
    (18, 5), -- Hammer Curl
    (19, 5), -- Preacher Curl
    (20, 5), -- Cable Curl

    -- Legs - Hamstring
    (21, 6), -- Romanian Deadlift
    (22, 6), -- Lying Leg Curl
    (23, 6), -- Seated Leg Curl
    (24, 6); -- Good Mornings
    
-- INSERT INTO user_goals (user_id, goal_type, target_weight, weekly_visits, start_date, end_date)
-- VALUES
--     (1, 'BUILD_MUSCLE', 65.00, 4, '2024-09-01', '2025-03-01');

INSERT INTO daily_goals (user_id, day_workout_id, exercise_id, completed)
VALUES
    (1, 1, 1, TRUE),
    (1, 1, 2, TRUE),
    (1, 1, 3, TRUE),
    (1, 2, 5, TRUE),   -- Pull-Up
    (1, 2, 6, TRUE),   -- Barbell Row
    (1, 2, 7, TRUE);   -- Lat Pulldown

INSERT INTO weekly_goals (user_id, week_start, week_end, gym_visits, all_workouts_completed)
VALUES
    (1, '2024-09-09', '2024-09-15', 4, TRUE),
    (1, '2024-09-16', '2024-09-22', 5, TRUE);