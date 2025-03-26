package learn.app.controller;

import learn.app.domain.ExerciseService;
import learn.app.models.workout.Exercise;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("flexio/exercise")
public class ExerciseController {

    private final ExerciseService service;

    public ExerciseController(ExerciseService service) {
        this.service = service;
    }

    // Operations
}
