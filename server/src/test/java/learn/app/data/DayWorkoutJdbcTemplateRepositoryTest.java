package learn.app.data;

import learn.app.models.workout.DayWorkout;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class DayWorkoutJdbcTemplateRepositoryTest {

    @Autowired
    DayWorkoutRepository repository;

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
    void findAllWorkouts() {
        List<DayWorkout> workouts = repository.findAllWorkouts();
        assertNotNull(workouts);
        assertEquals(2, workouts.size());
    }

    // (1, '2025-04-01', 'Upper Body'),
    @Test
    void findWorkoutById() {
        DayWorkout workout = repository.findWorkoutById(1);
        assertEquals(LocalDate.of(2025, 4, 1), workout.getDate());
        assertEquals("Upper Body", workout.getWorkoutName());
    }

    @Test
    void addWorkout() {
        DayWorkout workout = new DayWorkout();
        workout.setWorkoutName("Abs");
        workout.setDate(LocalDate.of(2025, 4, 4));
        DayWorkout addedWorkout = repository.addWorkout(workout);
        assertEquals(4, addedWorkout.getDayWorkoutId());
    }

    @Test
    void updateWorkout() {
        DayWorkout workout = new DayWorkout();
        workout.setWorkoutName("Abs");
        workout.setDate(LocalDate.of(2025, 4, 4));
        assertTrue(repository.updateWorkout(workout));
    }

//    @Test
//    void deleteWorkout() {
//        assertTrue(repository.deleteWorkout(2));
//    }
}