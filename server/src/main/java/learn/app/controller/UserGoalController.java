package learn.app.controller;

import learn.app.domain.UserGoalService;
import learn.app.models.goals.UserGoal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("flexio/user-goal")
public class UserGoalController {

    private final UserGoalService service;

    public UserGoalController(UserGoalService service) {
        this.service = service;
    }

    @GetMapping("/{userGoalId}")
    public ResponseEntity<UserGoal> findUserGoalById(@PathVariable int userGoalId) {
        UserGoal userGoal = service.findUserGoal(userGoalId);
        if (userGoal == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userGoal, HttpStatus.OK);
    }

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
