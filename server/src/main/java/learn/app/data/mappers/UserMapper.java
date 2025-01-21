package learn.app.data.mappers;

import learn.app.models.user.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {

        User user = new User();
        user.setUserId(resultSet.getInt("user_id"));
        user.setUsername(resultSet.getString("username"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setWeight(resultSet.getDouble("weight"));
        user.setHeightFt(resultSet.getInt("height_ft"));
        user.setHeightIn(resultSet.getInt("height_in"));

        return user;
    }
}
