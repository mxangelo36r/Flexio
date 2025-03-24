package learn.app.data.mappers;

import learn.app.models.workout.Exercise;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ExerciseMapper implements RowMapper<Exercise> {

    @Override
    public Exercise mapRow(ResultSet resultSet, int i) throws SQLException {

        Exercise exercise = new Exercise();
        exercise.setExerciseId(resultSet.getInt("exercise_id"));
        exercise.setExerciseName(resultSet.getString("name_exercise"));
        exercise.setWeight(resultSet.getDouble("weight"));
        exercise.setSets(resultSet.getInt("sets"));
        exercise.setReps(resultSet.getInt("reps"));

        return exercise;
    }
}
