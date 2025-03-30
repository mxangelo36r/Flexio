package learn.app.models.workout;

import javax.validation.constraints.*;

import java.util.Objects;

public class Exercise {

    private int exerciseId;
    @NotNull (message = "Exercise name cannot be empty")
    @NotBlank (message = "Exercise name cannot be empty")
    private String exerciseName;
    @NotNull (message = "Weight cannot be empty")
    @NotBlank (message = "Weight cannot be empty")
    @Min(value = 1, message = "Weight has to be greater than 0")
    private double weight;
    @NotNull (message = "Set cannot be empty")
    @NotBlank (message = "Set cannot be empty")
    @Min(value = 1, message = "Set has to be greater than 0")
    private int sets;
    @NotNull (message = "Reps cannot be empty")
    @NotBlank (message = "Reps cannot be empty")
    @Min(value = 1, message = "Reps has to be greater than 0")
    private int reps;

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

    // Equals & Hashcode

    // Probably needs editing:
    // Do we want to compare using other fields?

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
