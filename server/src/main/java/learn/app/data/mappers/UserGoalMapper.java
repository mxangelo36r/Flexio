package learn.app.data.mappers;

import learn.app.models.goals.UserGoal;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserGoalMapper implements RowMapper<UserGoal> {

    @Override
    public UserGoal mapRow(ResultSet resultSet, int i) throws SQLException {
        UserGoal userGoal = new UserGoal();
        userGoal.setUserGoalId(resultSet.getInt("user_goal_id"));
        userGoal.setUserId(resultSet.getInt("user_id"));
        userGoal.setTargetWeight(resultSet.getDouble("target_weight"));
        userGoal.setWeeklyVisits(resultSet.getInt("weekly_visits"));
        userGoal.setStartDate(resultSet.getDate("start_date").toLocalDate());
        userGoal.setEndDate(resultSet.getDate("end_date").toLocalDate());
        return userGoal;
    }
}
