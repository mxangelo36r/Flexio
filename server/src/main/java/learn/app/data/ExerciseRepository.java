package learn.app.data;

import learn.app.models.workout.Exercise;

import java.time.LocalDate;
import java.util.*;

public interface ExerciseRepository {

    List<Exercise> findAllExercises();
    Exercise findExerciseById(int id);
    Exercise addExercise(Exercise exercise);
    boolean updateExercise(Exercise exercise);
    boolean deleteExercise(int id);
    boolean existsByNameAndDay(String exerciseName, int dayWorkOutId);
}
