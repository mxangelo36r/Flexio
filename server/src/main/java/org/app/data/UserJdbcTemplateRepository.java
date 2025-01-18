package org.app.data;

import org.app.data.mappers.UserMapper;
import org.app.models.user.User;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserJdbcTemplateRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAllUsers() {

        final String sql = "SELECT user_id, username, email, password, weight, height_ft, height_in FROM users;";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    @Override
    public User findById(int id) {

        final String sql = "SELECT user_id, username, email, `password`, weight, height_ft, height_in "
                + "FROM users "
                + "WHERE user_id = ?;";

        try {
            return jdbcTemplate.queryForObject(sql, new UserMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public User addUser(User user) {

        final String sql = "INSERT INTO users (username, email, `password`, weight, height_ft, height_in) "
                + "VALUES (?, ?, ?, ?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setDouble(4, user.getWeight());
            ps.setInt(5, user.getHeightFt());
            ps.setInt(6, user.getHeightIn());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        user.setUserId(keyHolder.getKey().intValue());
        return user;
    }

    @Override
    public boolean updateUser(User user) {

        final String sql = "UPDATE users SET " +
                "username = ?, " +
                "email = ?, " +
                "`password` = ?, " +
                "weight = ?, " +
                "height_ft = ?, " +
                "height_in = ?;";

        int rowsUpdated = jdbcTemplate.update(sql, user.getUsername(), user.getEmail(), user.getPassword(),
                user.getWeight(), user.getHeightFt(), user.getHeightIn());

        return rowsUpdated > 0;
    }

    @Override
    public boolean deleteUser(int id) {

        final String sql = "DELETE FROM users WHERE user_id = ?;";
        return jdbcTemplate.update(sql, id) > 0;
    }
}
