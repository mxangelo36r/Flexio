package learn.app.data;

import learn.app.models.goals.GoalType;
import learn.app.models.goals.UserGoal;
import learn.app.models.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserGoalJdbcTemplateRepositoryTest {

    @Autowired
    UserGoalRepository repository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    static boolean hasSetUp = false;

    @BeforeEach
    void setup() {
        if (!hasSetUp) {
            hasSetUp = true;
            jdbcTemplate.update("call set_known_good_state();");
        }
    }

    @Test
    void shouldFindUserGoal() {

        UserGoal userGoal = repository.findUserGoal(1);
        assertNotNull(userGoal);
        assertEquals(GoalType.GET_STRONGER, userGoal.getGoalType()); // * Might not read properly
        assertEquals(75.00, userGoal.getTarget_weight());
        assertEquals(6, userGoal.getWeekly_visits());
        assertEquals(java.sql.Date.valueOf(LocalDate.of(2025, 3, 1)), userGoal.getStart_date());
        assertEquals(java.sql.Date.valueOf(LocalDate.of(2025, 9, 1)), userGoal.getEnd_date());
    }

    @Test
    void shouldAddUserGoal() {

        UserGoal userGoal = new UserGoal();
        userGoal.setUser_id(1);
        userGoal.setGoalType(GoalType.WEIGHT_LOSS);
        userGoal.setTarget_weight(70.0);
        userGoal.setWeekly_visits(4);
        userGoal.setStart_date(java.sql.Date.valueOf(LocalDate.of(2024, 2, 24)));
        userGoal.setEnd_date(java.sql.Date.valueOf(LocalDate.of(2025, 2, 1)));

        UserGoal addedUserGoal = repository.addUserGoal(userGoal);
        assertNotNull(addedUserGoal);
        assertEquals(2, addedUserGoal.getUser_goal_id());
    }

    @Test
    void updateUserGoal() {

        UserGoal userGoal = new UserGoal();
        userGoal.setUser_id(2);
        userGoal.setGoalType(GoalType.BUILD_MUSCLE);
        userGoal.setTarget_weight(83.00);
        userGoal.setStart_date(java.sql.Date.valueOf(LocalDate.of(2025, 5, 1)));
        userGoal.setStart_date(java.sql.Date.valueOf(LocalDate.of(2025, 12, 1)));

        assertTrue(repository.updateUserGoal(userGoal));
        assertEquals(java.sql.Date.valueOf(LocalDate.of(2025, 3, 1)), repository.findUserGoal(1).getStart_date());
    }
}

