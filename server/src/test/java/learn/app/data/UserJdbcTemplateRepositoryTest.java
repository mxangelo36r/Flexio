package learn.app.data;

import learn.app.models.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserJdbcTemplateRepositoryTest {

    @Autowired
    UserJdbcTemplateRepository repository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    static boolean hasSetUp = false;

    @BeforeEach
    void setup() {
        if (!hasSetUp) {
            hasSetUp = true;
            jdbcTemplate.update("call set_known_good_state();");
        }
    }

//    public UserJdbcTemplateRepositoryTest() {
//        ApplicationContext context = new AnnotationConfigApplicationContext(DbTestConfig.class);
//        repository = context.getBean(UserJdbcTemplateRepository.class);
//    }
//
//    @BeforeAll
//    static void oneTimeSetup() {
//        ApplicationContext context = new AnnotationConfigApplicationContext(DbTestConfig.class);
//        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
//        jdbcTemplate.update("call set_known_good_state();");
//    }

    @Test
    void shouldFindAllUsers() {

        List<User> users = repository.findAllUsers();

        assertNotNull(users);
        assertEquals(3, users.size());
    }

    @Test
    void shouldFindById() {

        User user = repository.findById(1);
        assertNotNull(user);
        assertEquals("testUsername", user.getUsername());
        assertEquals("testEmail@gmail.com", user.getEmail());
        assertEquals("testPassword", user.getPassword());
        assertEquals(50, user.getWeight());
        assertEquals(6, user.getHeightFt());
        assertEquals(4, user.getHeightIn());
    }

    @Test
    void shouldNotFindNullUser() {

        User user = repository.findById(3245);
        assertNull(user);
    }

    @Test
    void addUser() {

        User user = new User();
        user.setUsername("anotherUsername");
        user.setEmail("anotherEmail@gmail.com");
        user.setPassword("anotherPassword");
        user.setWeight(100);
        user.setHeightFt(7);
        user.setHeightIn(1);

        User addedUser = repository.addUser(user);
        assertNotNull(addedUser);
        assertEquals(3, addedUser.getUserId());
    }

    @Test
    void updateUser() {

        User user = new User();
        user.setUserId(1);
        user.setUsername("anotherUsername");
        user.setEmail("anotherEmail@gmail.com");
        user.setPassword("anotherPassword");
        user.setWeight(100);
        user.setHeightFt(7);
        user.setHeightIn(1);

        assertTrue(repository.updateUser(user));
        assertEquals("anotherUsername", repository.findById(1).getUsername());
    }

    @Test
    void shouldNotUpdateUser() {

        User updatedUser = new User();
        updatedUser.setUserId(123);
        updatedUser.setUsername("lskdjf");
        updatedUser.setEmail("akqkq");
        updatedUser.setPassword("109278ohjksand");
        updatedUser.setWeight(1);
        updatedUser.setHeightFt(70);
        updatedUser.setHeightIn(13);

        assertFalse(repository.updateUser(updatedUser));
    }

    @Test
    void shouldDeleteUser() {

        assertTrue(repository.deleteUser(1));
    }

    @Test
    void shouldNotDeleteUser() {

        assertFalse(repository.deleteUser(1000));
    }
}