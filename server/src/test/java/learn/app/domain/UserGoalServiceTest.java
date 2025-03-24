package learn.app.domain;

import learn.app.data.UserGoalRepository;
import learn.app.models.goals.GoalType;
import learn.app.models.goals.UserGoal;
import learn.app.models.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserGoalServiceTest {

    // Validations to test:
    // Not Null: user_id, goal type, target weight, weekly visits, start date, end date
    // Positive value: target weight, weekly visits
    // Future: start date, end date

    @Autowired
    UserGoalService service;

    @MockBean
    UserGoalRepository repository;

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    void shouldHaveValidParameters() {
        UserGoal userGoal = makeUserGoal();
        Set<ConstraintViolation<UserGoal>> violations = validator.validate(userGoal);
        assertFalse(violations.isEmpty());
        // Need to see what messages are being printed out
    }

    // Tried to see what messages are being printed out
    // Come back and try again
    @Test
    void shouldNotHaveValidParametersWithMessages() {
        UserGoal userGoal = makeUserGoal();
        Set<ConstraintViolation<UserGoal>> violations = validator.validate(userGoal);

//        List<String> messages = violations.stream()
//                .map(ConstraintViolation::getMessage)
//                .collect(Collectors.toList());

        Map<String, String> violationMap = violations.stream()
                .collect(Collectors.toMap(
                        violation -> violation.getPropertyPath().toString(),
                        ConstraintViolation::getMessage
                ));

        assertEquals("dslkfnldf", violationMap.get("user_id"));
    }
    // Implement happy and unhappy paths for UserGoal CRUD operations
    // Mockito etc.

    @Test
    void shouldFindUserGoal() {

        User user = makeUser();
        UserGoal userGoalIn = makeUserGoal();
        UserGoal userGoalOut = new UserGoal(
                1, 1, GoalType.WEIGHT_LOSS, 50.00, 3,
                java.sql.Date.valueOf(LocalDate.of(2025, 7, 1)),
                java.sql.Date.valueOf(LocalDate.of(2025, 12, 1))
        );

        when(repository.addUserGoal(userGoalIn)).thenReturn(userGoalOut);
    }

    @Test
    void addUserGoal() {
    }

    @Test
    void updateUserGoal() {
    }

    // Helper Methods
    User makeUser() {
        return new User(1, "mockUsername", "mockEmail@gmail.org", "mockPassword",
                50, 5, 4);
    }
    UserGoal makeUserGoal() {
        return new UserGoal(
                1, 1, GoalType.BUILD_MUSCLE, 67.35, 5,
                java.sql.Date.valueOf(LocalDate.of(2025, 1, 1)),
                java.sql.Date.valueOf(LocalDate.of(2025, 6, 1))
        );
    }
}