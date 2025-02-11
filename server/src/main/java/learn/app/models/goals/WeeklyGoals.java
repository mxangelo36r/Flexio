package learn.app.models.goals;

import learn.app.models.user.User;

import java.time.LocalDate;

public class WeeklyGoals {

    private int weekly_goal_id;
    private User user;
    private LocalDate week_start;
    private LocalDate week_end;
    private int gym_visits;
    private boolean workout_completed;

    // Constructors
    public WeeklyGoals() {

    }
    public WeeklyGoals(int weekly_goal_id, User user, LocalDate week_start, LocalDate week_end, int gym_visits, boolean workout_completed) {
        this.weekly_goal_id = weekly_goal_id;
        this.user = user;
        this.week_start = week_start;
        this.week_end = week_end;
        this.gym_visits = gym_visits;
        this.workout_completed = workout_completed;
    }

    // Getters & Setters
    public int getWeekly_goal_id() {
        return weekly_goal_id;
    }

    public void setWeekly_goal_id(int weekly_goal_id) {
        this.weekly_goal_id = weekly_goal_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getWeek_start() {
        return week_start;
    }

    public void setWeek_start(LocalDate week_start) {
        this.week_start = week_start;
    }

    public LocalDate getWeek_end() {
        return week_end;
    }

    public void setWeek_end(LocalDate week_end) {
        this.week_end = week_end;
    }

    public int getGym_visits() {
        return gym_visits;
    }

    public void setGym_visits(int gym_visits) {
        this.gym_visits = gym_visits;
    }

    public boolean isWorkout_completed() {
        return workout_completed;
    }

    public void setWorkout_completed(boolean workout_completed) {
        this.workout_completed = workout_completed;
    }
}

