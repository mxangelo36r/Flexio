package learn.app.data;

import learn.app.models.workout.Exercise;
import java.util.*;

public interface ExerciseRepository {

    List<Exercise> findAllExercises();
    Exercise findExerciseById(int id);
    Exercise addExercise(Exercise exercise);
    boolean updateExercise(Exercise exercise);
    boolean deleteExercise(int id);
}
