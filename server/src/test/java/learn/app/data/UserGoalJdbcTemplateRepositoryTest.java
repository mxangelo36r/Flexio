package learn.app.data;

import learn.app.models.goals.UserGoal;
import learn.app.models.goals.GoalType;
import org.apache.tomcat.jni.Local;
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
        assertEquals(5, userGoal.getWeeklyVisits());
        assertEquals(70.00, userGoal.getTargetWeight());
        assertEquals(LocalDate.of(2025, 5, 1), userGoal.getStartDate());
        assertEquals(LocalDate.of(2025, 12, 1), userGoal.getEndDate());

    }

    @Test
    void shouldAddUserGoal() {
        UserGoal userGoal = anotherUserGoal();
        UserGoal addedUserGoal = repository.addUserGoal(userGoal);
        assertEquals(2, addedUserGoal.getUserGoalId());
    }

    @Test
    void updateUserGoal() {
        UserGoal userGoal = new UserGoal();
        userGoal.setUserGoalId(1);
        userGoal.setUserId(1);
        userGoal.setGoalType(GoalType.WEIGHT_LOSS);
        userGoal.setWeeklyVisits(7);
        userGoal.setTargetWeight(60.00);
        userGoal.setStartDate(LocalDate.of(2025, 1, 1));
        userGoal.setEndDate(LocalDate.of(2025, 12, 1));

        assertTrue(repository.updateUserGoal(userGoal));
        assertEquals(7, repository.findUserGoalById(1).getWeeklyVisits());
    }

    // Helper Methods
    public UserGoal anotherUserGoal() {
        UserGoal anotherUserGoal = new UserGoal(1, 1, GoalType.MAINTAIN_WEIGHT,
                70.00, 3, LocalDate.of(2025, 6, 1),
                LocalDate.of(2026, 2, 1));

        return anotherUserGoal;
    }
}