package learn.app.data;

import learn.app.data.mappers.DayWorkoutMapper;
import learn.app.models.workout.DayWorkout;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class DayWorkoutJdbcTemplateRepository implements DayWorkoutRepository {

    private final JdbcTemplate jdbcTemplate;

    public DayWorkoutJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<DayWorkout> findAllWorkouts() {
        final String sql = "SELECT day_workout_id, day, name FROM day_workout;";
        return jdbcTemplate.query(sql, new DayWorkoutMapper());
    }

    @Override
    public DayWorkout findWorkoutById(int id) {
        final String sql = "SELECT day_workout_id, day, name FROM day_workout " +
                "WHERE day_workout_id = ?;";

        try {
            return jdbcTemplate.queryForObject(sql, new DayWorkoutMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public DayWorkout addWorkout(DayWorkout dayWorkout) {
        final String sql = "INSERT INTO day_workout (day, name)" +
                "VALUES (?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, java.sql.Date.valueOf(dayWorkout.getDate()));
            ps.setString(2, dayWorkout.getWorkoutName());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        dayWorkout.setDayWorkoutId(keyHolder.getKey().intValue());
        return dayWorkout;
    }

    @Override
    public boolean updateWorkout(DayWorkout dayWorkout) {
        final String sql = "UPDATE day_workout SET " +
                "day = ?, " +
                "name = ?;";

        int rowsUpdated = jdbcTemplate.update(sql,
                dayWorkout.getDate(),
                dayWorkout.getWorkoutName());

        return rowsUpdated > 0;
    }

    @Override
    public boolean deleteWorkout(int id) {
        String deleteDailyGoalsSql = "DELETE FROM daily_goals WHERE day_workout_id = ?;";
        jdbcTemplate.update(deleteDailyGoalsSql, id);

        final String deleteDayWorkoutFromExerciseBridge = "DELETE FROM day_workout_exercise WHERE day_workout_id = ?;";
        jdbcTemplate.update(deleteDayWorkoutFromExerciseBridge, id);

        final String deleteDayWorkoutFromProgramBridge = "DELETE FROM day_workout_weekly_program WHERE day_workout_id = ?;";
        jdbcTemplate.update(deleteDayWorkoutFromProgramBridge, id);

        final String deleteDayWorkoutSql = "DELETE FROM day_workout WHERE day_workout_id = ?;";
        return jdbcTemplate.update(deleteDayWorkoutSql, id) > 0;
    }
}
