package learn.app.data;

import learn.app.models.goals.UserGoal;
import learn.app.models.goals.GoalType;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.swing.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserGoalJdbcTemplateRepositoryTest {

    @Autowired
    UserGoalJdbcTemplateRepository repository;

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

//    (1, 'GET_STRONGER', 70.00, 5, '2025-05-01', '2025-12-01');
    @Test
    void shouldFindUserGoalById() {
        UserGoal userGoal = repository.findUserGoalById(1);
        assertEquals(1, userGoal.getUserId());
        assertEquals(GoalType.GET_STRONGER, userGoal.getGoalType());
        assertEquals(70.00, userGoal.getTargetWeight());
        assertEquals(LocalDate.of(2025, 5, 1), userGoal.getStartDate());
        assertEquals(LocalDate.of(2025, 12, 1), userGoal.getEndDate());

    }

    @Test
    void addUserGoal() {
    }

    @Test
    void updateUserGoal() {
    }
}