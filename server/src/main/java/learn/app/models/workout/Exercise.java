package learn.app.models.workout;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Min;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int exerciseId;
    @NotNull (message = "Exercise name cannot be empty")
    @NotBlank (message = "Exercise name cannot be empty")
    private String exerciseName;

    @Min(value = 1, message = "Weight has to be greater than 0")
    private double weight;

    @Min(value = 1, message = "Set has to be greater than 0")
    private int sets;

    @Min(value = 1, message = "Reps has to be greater than 0")
    private int reps;

    @ManyToMany(mappedBy = "exercises")
    private Set<DayWorkout> dayWorkouts = new HashSet<>();

    public Exercise() {

    }

    public Exercise (int exerciseId, String exerciseName, double weight, int sets, int reps) {

        this.exerciseId = exerciseId;
        this.exerciseName = exerciseName;
        this.weight = weight;
        this.sets = sets;
        this.reps = reps;
    }

    // Getters & Setters
    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    // Day Workouts

    public Set<DayWorkout> getDayWorkouts() {
        return dayWorkouts;
    }

    public void setDayWorkouts(Set<DayWorkout> dayWorkouts) {
        this.dayWorkouts = dayWorkouts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exercise exercise = (Exercise) o;
        return exerciseId == exercise.exerciseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(exerciseId);
    }
}
