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
import java.util.List;

@Repository
public class ExerciseJdbcTemplateRepository implements ExerciseRepository {
    @Override
    public List<Exercise> findAllExercises() {
        return null;
    }

    @Override
    public Exercise findExerciseById(int id) {
        return null;
    }

    @Override
    public Exercise addExercise(Exercise exercise) {
        return null;
    }

    @Override
    public boolean updateExercise(Exercise exercise) {
        return false;
    }

    @Override
    public boolean deleteExercise(int id) {
        return false;
    }
}
