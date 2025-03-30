package learn.app.controller;

import learn.app.domain.ExerciseService;
import learn.app.domain.result.Result;
import learn.app.models.workout.Exercise;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/add")
    public ResponseEntity<Object> addExercise(@Valid @RequestBody Exercise exercise) {
        Exercise result = service.addExercise(exercise);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    // Printing out validations
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }


}
