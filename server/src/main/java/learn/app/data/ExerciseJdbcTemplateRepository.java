package learn.app.data;

import learn.app.data.mappers.ExerciseMapper;
import learn.app.models.workout.Exercise;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

@Repository
public class ExerciseJdbcTemplateRepository implements ExerciseRepository {

    private final JdbcTemplate jdbcTemplate;

    public ExerciseJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Exercise> findAllExercises() {
        final String sql = "SELECT exercise_id, name_exercise, weight, sets, reps FROM exercise;";
        return jdbcTemplate.query(sql, new ExerciseMapper());
    }

    @Override
    public Exercise findExerciseById(int id) {
        final String sql = "SELECT exercise_id, name_exercise, weight, sets, reps " +
                "FROM exercise " +
                "WHERE exercise_id = ?;";

        try {
            return jdbcTemplate.queryForObject(sql, new ExerciseMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public Exercise addExercise(Exercise exercise) {
        final String sql = "INSERT INTO exercise (name_exercise, weight, sets, reps) " +
                "VALUES (?, ?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, exercise.getExerciseName());
            ps.setDouble(2, exercise.getWeight());
            ps.setInt(3, exercise.getSets());
            ps.setInt(4, exercise.getReps());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        exercise.setExerciseId(keyHolder.getKey().intValue());
        return exercise;
    }

    @Override
    public boolean updateExercise(Exercise exercise) {
        final String sql = "UPDATE exercise SET " +
                "name_exercise = ?, " +
                "weight = ?, " +
                "sets = ?, " +
                "reps = ? " +
                "WHERE exercise_id = ?;";

        int rowsUpdated = jdbcTemplate.update(sql,
                exercise.getExerciseName(),
                exercise.getWeight(),
                exercise.getSets(),
                exercise.getReps(),
                exercise.getExerciseId() > 0);

        return rowsUpdated > 0;
    }

    @Override
    public boolean deleteExercise(int id) {
//        String deleteDailyGoalsSql = "DELETE FROM daily_goals WHERE exercise_id = ?;";
//        jdbcTemplate.update(deleteDailyGoalsSql, id);

        String deleteDayWorkoutExerciseSql = "DELETE FROM day_workout_exercise WHERE exercise_id = ?;";
        jdbcTemplate.update(deleteDayWorkoutExerciseSql, id);

        String deleteExerciseSql = "DELETE FROM exercise WHERE exercise_id = ?;";

        return jdbcTemplate.update(deleteExerciseSql, id) > 0;
    }

    // Helper validation methods

    @Override
    public boolean existsByNameAndDay(String exerciseName, int dayWorkoutId) {
        final String sql = "SELECT * FROM day_workout_exercise dwe " +
                "JOIN exercise e " +
                "ON dwe.exercise_id = e.exercise_id " +
                "WHERE e.name_exercise = ? AND dwe.day_workout_id = ?;";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{exerciseName, dayWorkoutId}, Integer.class);
        return count != null && count > 0;
    }

    // To Do:
    // Need to be able to add exercise for a given DayWorkout
    // Need to remove dayworkout from exercises e.g dayWorkouts[]
}
