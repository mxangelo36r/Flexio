package learn.app.domain;

import learn.app.data.ExerciseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ExerciseServiceTest {

    @MockBean
    ExerciseRepository repository;

    @Autowired
    ExerciseService service;

    @Test
    void findAllExercise() {
    }

    @Test
    void findExerciseById() {
    }

    @Test
    void addExercise() {
    }

    @Test
    void updateExercise() {
    }

    @Test
    void deleteExercise() {
    }
}