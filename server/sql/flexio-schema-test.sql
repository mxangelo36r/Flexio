drop database if exists flexio_test;
create database flexio_test;
use flexio_test;

-- User
CREATE TABLE users (

	user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(250) NOT NULL,
    `password` VARCHAR(200) NOT NULL,
    weight DOUBLE NOT NULL, -- For now weight is in KG 
    height_ft INT NOT NULL,
    height_in INT NOT NULL
);

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

-- CREATE TABLE daily_goals (
--     daily_goal_id INT AUTO_INCREMENT PRIMARY KEY, -- Unique identifier for each daily goal
--     user_id INT,                                  -- ID of the user
--     day_workout_id INT,                           -- ID of the day workout
--     exercise_id INT,                              -- ID of the exercise
--     workout_date DATE,                            -- Date of the workout
--     completed BOOLEAN,                            -- Status of goal completion
--     UNIQUE KEY unique_user_exercise (user_id, exercise_id), -- Ensures a user has only one entry per exercise
--     FOREIGN KEY (exercise_id, day_workout_id) REFERENCES day_workout_exercise(exercise_id, day_workout_id)
-- );

-- CREATE TABLE day_workout_weekly_program (

-- 	day_workout_id INT,
--     weekly_program_id INT,
-- 	PRIMARY KEY (day_workout_id, weekly_program_id),
--     FOREIGN KEY (day_workout_id) REFERENCES day_workout(day_workout_id) ON DELETE CASCADE ON UPDATE CASCADE,
--     FOREIGN KEY (weekly_program_id) REFERENCES weekly_program(weekly_program_id) ON DELETE CASCADE ON UPDATE CASCADE
-- );


-- Procedure
delimiter //
CREATE PROCEDURE set_known_good_state()
BEGIN
	-- Truncating tables
    DELETE FROM user_goals;
    ALTER TABLE user_goals AUTO_INCREMENT = 1;
	DELETE FROM users;
    ALTER TABLE users AUTO_INCREMENT = 1;
    DELETE FROM	exercise;
    ALTER TABLE exercise AUTO_INCREMENT = 1;
    DELETE FROM day_workout;
    ALTER TABLE day_workout AUTO_INCREMENT = 1;
    DELETE FROM day_workout_exercise;
    ALTER TABLE day_workout_exercise AUTO_INCREMENT = 1;
    DELETE FROM user_goals;
    ALTER TABLE user_goals AUTO_INCREMENT = 1;
--     DELETE FROM daily_goals;
--     ALTER TABLE daily_goals AUTO_INCREMENT = 1;
--     DELETE FROM day_workout_weekly_program;
--     ALTER TABLE day_workout_weekly_program AUTO_INCREMENT = 1;
    

    -- Adding Data
    INSERT INTO users (username, email, `password`, weight, height_ft, height_in)
    VALUES 
		('testUsername', 'testEmail@gmail.com', 'testPassword', 50, 6, 4),
        ('testUsernameTwo', 'testEmailTwo@gmail.com', 'testPasswordTwo', 67, 5, 11);
        
        
	INSERT INTO exercise (exercise_id, name_exercise, weight, sets, reps)
    VALUES
		(1, 'Incline Bench Press', 90, 4, 12),
        (2, 'Dumbbell Bench Press', 100, 3, 10),
        (3, 'Rope Pulldowns', 110, 3, 10);
        
	INSERT INTO day_workout (day_workout_id, `day`, `name`)
	VALUES
		(1, '2025-04-01', 'Upper Body'),
		(2, '2025-04-02', 'Lower Body');
        
	INSERT INTO day_workout_exercise (exercise_id, day_workout_id)
	VALUES
		(1, 1), 
		(2, 1),
		(3, 2);
        
    INSERT INTO user_goals (user_goal_id, user_id, goal_type, target_weight, weekly_visits, start_date, end_date)
	VALUES
		(1, 1, 'GET_STRONGER', 70.00, 5, '2025-05-01', '2025-12-01');    
        
END //
delimiter ;