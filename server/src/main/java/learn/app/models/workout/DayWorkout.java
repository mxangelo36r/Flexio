package learn.app.models.workout;

import java.time.LocalDate;

public class DayWorkout {

    // Fields
    private int dayWorkoutId;
    private LocalDate date;
    private String workoutName;

    // Constructor
    public DayWorkout(int dayWorkoutId, LocalDate date, String workoutName) {

        this.dayWorkoutId = dayWorkoutId;
        this.date = date;
        this.workoutName = workoutName;
    }

    // Getters & Setters

    public int getDayWorkoutId() {
        return dayWorkoutId;
    }

    public void setDayWorkoutId(int dayWorkoutId) {
        this.dayWorkoutId = dayWorkoutId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }
}
