package learn.app.data;

import learn.app.data.mappers.DayWorkoutMapper;
import learn.app.models.workout.DayWorkout;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
        final String sql = "SELECT day_workout_id, `day`, `name` FROM day_workout " +
                "WHERE day_workout_id = ?;";

        try {
            return jdbcTemplate.queryForObject(sql, new DayWorkoutMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public DayWorkout addWorkout(DayWorkout dayWorkout) {
        return null;
    }

    @Override
    public boolean updateWorkout(DayWorkout dayWorkout) {
        return false;
    }

    @Override
    public boolean deleteWorkout(DayWorkout dayWorkout) {
        return false;
    }
}
