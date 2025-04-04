package learn.app.validations;

import learn.app.data.ExerciseRepository;
import learn.app.models.workout.Exercise;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import learn.app.validations.UniqueExerciseName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class UniqueExerciseNameValidator implements ConstraintValidator<UniqueExerciseName, Exercise> {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Override
    public boolean isValid(Exercise exercise, ConstraintValidatorContext context) {
        // Implement logic to check for duplicate exercise names on the same day
        if (exercise ==  null) {
            return true;
        }
        return !exerciseRepository.existsByNameAndDay(exercise.getExerciseName(), exercise.getDayWorkout().getDayWorkoutId());
    }
}