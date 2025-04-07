package learn.app.domain;

import learn.app.data.DayWorkoutRepository;
import learn.app.models.workout.DayWorkout;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DayWorkoutService {

    private final DayWorkoutRepository repository;

    public DayWorkoutService(DayWorkoutRepository repository) {
        this.repository = repository;
    }

    public List<DayWorkout> findAllWorkouts() {
        return repository.findAllWorkouts();
    }

    public DayWorkout findWorkoutById(int id) {
        return repository.findWorkoutById(id);
    }

    public DayWorkout addWorkout(DayWorkout workout) {
        return repository.addWorkout(workout);
    }

    public boolean updateWorkout(DayWorkout workout) {
        return repository.updateWorkout(workout);
    }

    public boolean deleteWorkout(int id) {
        return repository.deleteWorkout(id);
    }
}

