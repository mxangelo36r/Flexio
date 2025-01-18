package org.app.data;

import org.app.data.mappers.UserMapper;
import org.app.models.user.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserJdbcTemplateRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAllUsers() {

        final String sql = "SELECT user_id, username, email, `password`, weight, height_ft, height_in FROM users;";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public boolean deleteUser(User user) {
        return false;
    }
}
