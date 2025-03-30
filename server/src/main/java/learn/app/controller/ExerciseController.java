package learn.app.controller;

import learn.app.domain.ExerciseService;
import learn.app.models.workout.Exercise;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("flexio/exercise")
public class ExerciseController {

    private final ExerciseService service;

    public ExerciseController(ExerciseService service) {
        this.service = service;
    }

    // Operations
    @GetMapping
    public List<Exercise> findAllExercises() {
        return service.findAllExercise();
    }

    @GetMapping("/{exerciseId}")
    public ResponseEntity<Exercise> findById(@PathVariable int exerciseId) {
        Exercise exercise = service.findExerciseById(exerciseId);
        if (exercise == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(exercise, HttpStatus.OK);
    }


}
