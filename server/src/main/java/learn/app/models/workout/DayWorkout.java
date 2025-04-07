package learn.app.models.workout;

import learn.app.validations.UniqueExerciseName;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@UniqueExerciseName
public class DayWorkout {

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dayWorkoutId;
    @NotNull (message = "Date cannot be empty")
    @NotBlank (message = "Date cannot be empty")
    @Past (message = "Date cannot be in the past")
    private LocalDate date;
    @NotNull (message = "Workout name cannot be empty")
    @NotBlank (message = "Workout name cannot be empty")
    private String workoutName;

     // Join Table
    @ManyToMany
    @JoinTable(
            name = "day_workout_exercise",
            joinColumns = @JoinColumn(name = "day_workout_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_id")
    )
    private Set<Exercise> exercises = new HashSet<>();

    // Empty Constructor
    public DayWorkout() {

    }

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

    // Exercises

    public Set<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(Set<Exercise> exercises) {
        this.exercises = exercises;
    }
}
