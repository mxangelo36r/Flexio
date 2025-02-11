package learn.app.models.workout;


import java.util.Objects;

public class Exercise {

    private int exerciseId;
    private String exerciseName;
    private double weight;
    private int sets;
    private int reps;

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
