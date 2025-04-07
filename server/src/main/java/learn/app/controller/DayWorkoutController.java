package learn.app.controller;

import learn.app.domain.DayWorkoutService;
import learn.app.models.workout.DayWorkout;
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
@RequestMapping("flexio/dayworkout")
public class DayWorkoutController {

    private final DayWorkoutService service;

    public DayWorkoutController(DayWorkoutService service) {
        this.service = service;
    }

    @GetMapping
    public List<DayWorkout> findAllWorkouts() {
        return service.findAllWorkouts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DayWorkout> findWorkoutById(@PathVariable int id) {
        DayWorkout workout = service.findWorkoutById(id);
        if (workout == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(workout, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addWorkout(@Valid @RequestBody DayWorkout workout) {
        DayWorkout result = service.addWorkout(workout);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("update/{dayWorkoutId}")
    public ResponseEntity<Void> updateWorkout(@PathVariable int dayWorkoutId, @Valid @RequestBody DayWorkout workout) {
        if (dayWorkoutId != workout.getDayWorkoutId()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        boolean result = service.updateWorkout(workout);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable int id) {
        if (service.deleteWorkout(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidateExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
}
