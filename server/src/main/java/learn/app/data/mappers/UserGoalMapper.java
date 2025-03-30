//package learn.app.data.mappers;
//
//import learn.app.models.goals.GoalType;
//import learn.app.models.goals.UserGoal;
//import learn.app.models.user.User;
//import org.springframework.jdbc.core.RowMapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class UserGoalMapper implements RowMapper<UserGoal> {
//
//
//    @Override
//    public UserGoal mapRow(ResultSet resultSet, int i) throws SQLException {
//
//        UserGoal userGoal = new UserGoal();
//        userGoal.setUser_id(resultSet.getInt("user_id"));
//        userGoal.setGoalType(GoalType.valueOf(resultSet.getString("goal_type")));
//        userGoal.setTarget_weight(resultSet.getDouble("target_weight"));
//        userGoal.setWeekly_visits(resultSet.getInt("weekly_visits"));
//        userGoal.setStart_date(resultSet.getDate("start_date"));
//        userGoal.setEnd_date(resultSet.getDate("end_date"));
//
//        return userGoal;
//    }
//}
