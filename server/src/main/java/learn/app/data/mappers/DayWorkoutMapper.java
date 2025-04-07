package learn.app.data.mappers;

import learn.app.models.workout.DayWorkout;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class DayWorkoutMapper implements RowMapper<DayWorkout> {
    @Override
    public DayWorkout mapRow(ResultSet resultSet, int i) throws SQLException {
        DayWorkout dayWorkout = new DayWorkout();
        dayWorkout.setDayWorkoutId(resultSet.getInt("day_workout_id"));
        dayWorkout.setDate(resultSet.getDate("day").toLocalDate());
        dayWorkout.setWorkoutName(resultSet.getString("name"));
        return dayWorkout;
    }
}
