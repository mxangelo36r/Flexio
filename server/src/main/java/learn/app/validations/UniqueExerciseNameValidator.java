package learn.app.validations;

import learn.app.models.workout.DayWorkout;
import learn.app.models.workout.Exercise;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

public class UniqueExerciseNameValidator implements ConstraintValidator<UniqueExerciseName, DayWorkout> {

    @Override
    public boolean isValid(DayWorkout dayWorkout, ConstraintValidatorContext context) {
        Set<String> exerciseNames = new HashSet<>();
        for (Exercise exercise : dayWorkout.getExercises()) {
            if (!exerciseNames.add(exercise.getExerciseName())) {
                return false; // Duplicate exercise name found
            }
        }
        return true;
    }
}