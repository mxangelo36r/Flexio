package learn.app.data;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

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
    }

    @Test
    void findWorkoutById() {
    }

    @Test
    void addWorkout() {
    }

    @Test
    void updateWorkout() {
    }

    @Test
    void deleteWorkout() {
    }
}