package learn.app.data;

import learn.app.models.goals.UserGoal;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserGoalJdbcTemplateRepository implements UserGoalRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserGoalJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public UserGoal addUserGoal(UserGoal userGoal) {
        return null;
    }

    @Override
    public boolean updateUserGoal(UserGoal userGoal) {
        return false;
    }
}
