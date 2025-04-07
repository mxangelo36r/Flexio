//package learn.app.validations;
//
//import learn.app.data.ExerciseRepository;
//import learn.app.models.workout.DayWorkout;
//import learn.app.models.workout.Exercise;
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//
//import learn.app.validations.UniqueExerciseName;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public abstract class UniqueExerciseNameValidator implements ConstraintValidator<UniqueExerciseName, Exercise> {
//
////    @Override
////    public boolean isValid(Exercise exercise, ConstraintValidatorContext context) {
////        // Implement logic to check for duplicate exercise names on the same day
////        if (exercise ==  null || exercise.getDayWorkouts() == null) {
////            return true;
////        }
////
////        for (DayWorkout dayWorkout : exercise.getDayWorkouts()) {
////            boolean exists = dayWorkout.getExercises().stream()
////                    .anyMatch(e -> e.getExerciseName().equals(exercise.getExerciseName()) && !(e.getExerciseId() == exercise.getExerciseId()));
////
////            if (exists) {
////                return false;
////            }
////        }
////
////        return true;
////    }
//}