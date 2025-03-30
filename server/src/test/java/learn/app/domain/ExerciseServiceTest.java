package learn.app.domain;

import learn.app.models.workout.Exercise;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ExerciseServiceTest {

    @Autowired
    private static Validator validator;

    @BeforeAll
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidExercisesInputs() {
        Exercise validExerciseName = makeExercise();
        Set<ConstraintViolation<Exercise>> violations = validator.validate(validExerciseName);
        assertThat(violations).isEmpty();
    }

    @Test
    void findExerciseById() {
    }

    @Test
    void addExercise() {
    }

    @Test
    void updateExercise() {
    }

    @Test
    void deleteExercise() {
    }

    // Helper Methods
    Exercise makeExercise() {
        return new Exercise(1, "mockExercise", 100, 10 , 3);
    }
}