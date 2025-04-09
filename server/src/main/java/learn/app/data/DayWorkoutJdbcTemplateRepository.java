package learn.app.data;

import learn.app.data.mappers.DayWorkoutMapper;
import learn.app.models.workout.DayWorkout;
import learn.app.models.workout.Exercise;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DayWorkoutJdbcTemplateRepository implements DayWorkoutRepository {

    private final JdbcTemplate jdbcTemplate;

    public DayWorkoutJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<DayWorkout> findAllWorkouts() {
        final String sql = "SELECT dw.day_workout_id, dw.`day`, dw.`name`, " +
                "e.exercise_id, e.name_exercise, e.weight, e.sets, e.reps " +
                "FROM day_workout dw " +
                "INNER JOIN day_workout_exercise dwe ON dw.day_workout_id = dwe.day_workout_id " +
                "INNER JOIN exercise e ON e.exercise_id = dwe.exercise_id;";

        DayWorkoutWithExercisesMapper mapper = new DayWorkoutWithExercisesMapper();
        jdbcTemplate.query(sql, mapper); // Execute the query; mapper populates the map
        return mapper.getDayWorkouts();
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

    // Separate mapper class to retrieve workouts with exercises for FIND operations
    private static class DayWorkoutWithExercisesMapper implements RowMapper<DayWorkout> {
        private Map<Integer, DayWorkout> dayWorkoutMap = new HashMap<>();

        @Override
        public DayWorkout mapRow(ResultSet rs, int rowNum) throws SQLException {
            int dayWorkoutId = rs.getInt("day_workout_id");

            DayWorkout dayWorkout = dayWorkoutMap.computeIfAbsent(dayWorkoutId, k -> {
                DayWorkout dw = new DayWorkout();
                dw.setDayWorkoutId(dayWorkoutId);
                try {
                    dw.setDate(rs.getDate("day").toLocalDate());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    dw.setWorkoutName(rs.getString("name"));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                return dw;
            });

            if (rs.getObject("exercise_id") != null) {
                Exercise exercise = new Exercise();
                exercise.setExerciseId(rs.getInt("exercise_id"));
                exercise.setExerciseName(rs.getString("name_exercise"));
                exercise.setWeight(rs.getDouble("weight"));
                exercise.setSets(rs.getInt("sets"));
                exercise.setReps(rs.getInt("reps"));

                dayWorkout.getExercises().add(exercise);
            }

            return null;
        }

        public List<DayWorkout> getDayWorkouts() {
            return new ArrayList<>(dayWorkoutMap.values());
        }
    }
}
