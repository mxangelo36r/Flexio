package org.app.domain;

import org.app.data.UserRepository;
import org.app.domain.result.Result;
import org.app.domain.result.ResultType;
import org.app.models.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserServiceTest {

    @MockBean
    UserRepository repository;

    @Autowired
    UserService service;

    @Test
    void shouldAddUser() {
        User userIn = makeUser();
        User userOut = new User(2, "mockUsername", "mockEmail@gmail.org", "mockPassword",
                50, 5, 4);

        // Stubbing behaviour
        when(repository.addUser(userIn)).thenReturn(userOut);

        Result<User> result = service.addUser(userIn);

        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(userOut, result.getPayload());
    }

    

    // Helper Methods
    User makeUser() {
        return new User(1, "mockUsername", "mockEmail@gmail.org", "mockPassword",
                50, 5, 4);
    }

}