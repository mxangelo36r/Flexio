package learn.app.models.goals;

import learn.app.models.user.User;

import java.time.LocalDate;

public class UserGoal {

    private int user_goal_id;
    private User user;
    private GoalType goalType;
    private double target_weight;
    private LocalDate start_date;
    private LocalDate end_date;

    // Constructors

    public UserGoal() {

    }

    public UserGoal(int user_goal_id, User user, GoalType goalType, double target_weight, LocalDate start_date, LocalDate end_date) {
        this.user_goal_id = user_goal_id;
        this.user = user;
        this.goalType = goalType;
        this.target_weight = target_weight;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    // Getters & Setters

    public int getUser_goal_id() {
        return user_goal_id;
    }

    public void setUser_goal_id(int user_goal_id) {
        this.user_goal_id = user_goal_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GoalType getGoalType() {
        return goalType;
    }

    public void setGoalType(GoalType goalType) {
        this.goalType = goalType;
    }

    public double getTarget_weight() {
        return target_weight;
    }

    public void setTarget_weight(double target_weight) {
        this.target_weight = target_weight;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }
}
