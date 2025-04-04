package learn.app.domain;

import learn.app.data.ExerciseRepository;
import learn.app.models.workout.Exercise;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExerciseService {

    private final ExerciseRepository repository;

    public ExerciseService(ExerciseRepository repository) {
        this.repository = repository;
    }

    // CRUD Operations (Validated via Spring Boot Starter Validations -> See Exercise in models package)

    public List<Exercise> findAllExercise() {
        return repository.findAllExercises();
    }

    public Exercise findExerciseById(int id) {
        return repository.findExerciseById(id);
    }

    public Exercise addExercise(Exercise exercise) {
        return repository.addExercise(exercise);
    }

    public boolean updateExercise(Exercise exercise) {
        return repository.updateExercise(exercise);
    }

    public boolean deleteExercise(int id) {
        return repository.deleteExercise(id);
    }

    // Might need to add custom validations:
    // - Can't have duplicate exercise names on the same day
}
