package org.app.models.goals;

import org.app.models.User;

import java.time.LocalDate;

public class DailyGoals {

    private int daily_goal_id;
    private User user;
    private int day_workout_id;
    private int exercise_id;
    private LocalDate workout_date;
    private boolean completed;

    // Constructors

    public DailyGoals() {

    }

    public DailyGoals(int daily_goal_id, User user, int day_workout_id, int exercise_id, LocalDate workout_date, boolean completed) {
        this.daily_goal_id = daily_goal_id;
        this.user = user;
        this.day_workout_id = day_workout_id;
        this.exercise_id = exercise_id;
        this.workout_date = workout_date;
        this.completed = completed;
    }
}
