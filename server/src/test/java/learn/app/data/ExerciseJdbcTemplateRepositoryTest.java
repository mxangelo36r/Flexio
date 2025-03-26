package learn.app.data;

import learn.app.models.workout.Exercise;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ExerciseJdbcTemplateRepositoryTest {

    @Autowired
    ExerciseJdbcTemplateRepository repository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    static boolean hasSetUp = false;

    @BeforeEach
    void setup() {
        if (!hasSetUp) {
            hasSetUp = true;
            jdbcTemplate.update("call set_known_good_state");
        }
    }

    @Test
    void shouldFindAllExercises() {
        List<Exercise> exercises = repository.findAllExercises();
        assertNotNull(exercises);
        assertEquals(3, exercises.size());
    }

    @Test
    void shouldFindExerciseById() {
        Exercise exercise = repository.findExerciseById(2);
        assertEquals("Dumbbell Bench Press", exercise.getExerciseName());
        assertEquals(100, exercise.getWeight());
        assertEquals(3, exercise.getSets());
        assertEquals(10, exercise.getReps());
    }

    @Test
    void shouldNotFindNonExistentExercise() {
        Exercise exercise = repository.findExerciseById(123);
        assertNull(exercise);
    }

    @Test
    void addExercise() {
        Exercise newExercise = new Exercise();
        newExercise.setExerciseName("Lateral Raises");
        newExercise.setWeight(20);
        newExercise.setSets(3);
        newExercise.setReps(12);

        Exercise addedExercise = repository.addExercise(newExercise);
        assertNotNull(addedExercise);
        assertEquals(4, addedExercise.getExerciseId());
    }

    @Test
    void updateExercise() {
        Exercise updatedExercise = new Exercise();
        updatedExercise.setExerciseId(1);
        updatedExercise.setExerciseName("Dumbbell Incline Bench Press");
        updatedExercise.setWeight(70);
        updatedExercise.setSets(3);
        updatedExercise.setReps(8);

        assertTrue(repository.updateExercise(updatedExercise));
    }

    @Test
    void deleteExercise() {
        assertTrue(repository.deleteExercise(1));
    }
}