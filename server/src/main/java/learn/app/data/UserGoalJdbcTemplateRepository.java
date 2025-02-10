package learn.app.data;

import learn.app.models.goals.UserGoal;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UserGoalJdbcTemplateRepository implements UserGoalRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserGoalJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public UserGoal addUserGoal(UserGoal userGoal) {

        final String sql = "INSERT INTO user_goals (user_id, goal_type, target_weight, weekly_visits, start_date, " +
                "end_date) VALUES (?, ?, ?, ?, ?, ?);";

        KeyHolder keyholder = new GeneratedKeyHolder();

        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, userGoal.getUser_id());
            // Need to learn how to set an ENUM
            ps.setDouble(3, userGoal.getTarget_weight());
            ps.setInt(4, userGoal.getWeekly_visits());
            ps.setDate(5, userGoal.getStart_date());
            ps.setDate(6, userGoal.getEnd_date());
            return ps;
        }, keyholder);

        if (rowsAffected <= 0) {
            return null;
        }

        userGoal.setUser_id(keyholder.getKey().intValue());
        return userGoal;
    }

    @Override
    public boolean updateUserGoal(UserGoal userGoal) {
        return false;
    }
}
