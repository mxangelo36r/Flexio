package learn.app.models.goals;

import java.sql.Date;

public class UserGoal {

    private int user_goal_id;
    private int user_id;
    private GoalType goalType;
    private double target_weight;
    private int weekly_visits;
    private Date start_date;
    private Date end_date;

    // Constructors

    public UserGoal() {

    }

    public UserGoal(int user_goal_id, int user_id, GoalType goalType, double target_weight, int weekly_visits, Date start_date, Date end_date) {
        this.user_goal_id = user_goal_id;
        this.user_id = user_id;
        this.goalType = goalType;
        this.target_weight = target_weight;
        this.weekly_visits = weekly_visits;
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public int getWeekly_visits() {
        return weekly_visits;
    }

    public void setWeekly_visits(int weekly_visits) {
        this.weekly_visits = weekly_visits;
    }

    public java.sql.Date getStart_date() {
        return (java.sql.Date) start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public java.sql.Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }
}
