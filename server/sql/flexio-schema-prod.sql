drop database if exists flexio;
create database flexio;
use flexio;

-- USER

CREATE TABLE users (

	user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(250) NOT NULL,
    `password` VARCHAR(200) NOT NULL,
    weight DOUBLE NOT NULL,
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
    name VARCHAR(100)
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