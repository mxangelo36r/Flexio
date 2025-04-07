package learn.app.data;

import learn.app.models.workout.DayWorkout;

import java.util.List;

public interface DayWorkoutRepository {

    List<DayWorkout> findAllWorkouts();
    DayWorkout findWorkoutById(int id);
    DayWorkout addWorkout(DayWorkout dayWorkout);
    boolean updateWorkout(DayWorkout dayWorkout);
    boolean deleteWorkout(int id);
}
