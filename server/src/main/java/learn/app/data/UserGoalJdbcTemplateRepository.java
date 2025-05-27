package learn.app.data;

import learn.app.data.mappers.UserGoalMapper;
import learn.app.models.goals.UserGoal;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class UserGoalJdbcTemplateRepository implements UserGoalRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserGoalJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserGoal findUserGoalById(int id) {
        final String sql = "SELECT user_goal_id, user_id, goal_type, target_weight, weekly_visits, start_date, end_date " +
                "FROM user_goals " +
                "WHERE user_id = ?;";

        try {
            return jdbcTemplate.queryForObject(sql, new UserGoalMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public UserGoal addUserGoal(UserGoal userGoal) {
        final String sql = "INSERT INTO user_goals (user_id, goal_type, target_weight, weekly_visits, start_date, end_date) " +
                "VALUES (?, ?, ?, ?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, userGoal.getUserId());
            ps.setString(2, userGoal.getGoalType().name());
            ps.setDouble(3, userGoal.getTargetWeight());
            ps.setInt(4, userGoal.getWeeklyVisits());
            ps.setDate(5, java.sql.Date.valueOf(userGoal.getStartDate()));
            ps.setDate(6, java.sql.Date.valueOf(userGoal.getEndDate()));
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        userGoal.setUserGoalId(keyHolder.getKey().intValue());
        return userGoal;
    }

    @Override
    public boolean updateUserGoal(UserGoal userGoal) {
        final String sql = "UPDATE user_goals SET " +
                "goal_type = ?, " +
                "target_weight = ?, " +
                "weekly_visits = ?, " +
                "start_date = ?, " +
                "end_date = ? " +
                "WHERE user_goal_id = ? " +
                "AND user_id = ?;";

        int rowsUpdated = jdbcTemplate.update(sql,
                userGoal.getGoalType().name(),
                userGoal.getTargetWeight(),
                userGoal.getWeeklyVisits(),
                java.sql.Date.valueOf(userGoal.getStartDate()),
                java.sql.Date.valueOf(userGoal.getEndDate()),
                userGoal.getUserGoalId(),
                userGoal.getUserId());

        return rowsUpdated > 0;
    }
}
