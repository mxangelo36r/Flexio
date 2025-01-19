package org.app.domain;

import org.app.data.UserJdbcTemplateRepository;
import org.app.domain.result.Result;
import org.app.models.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserJdbcTemplateRepository repository;

    public UserService(UserJdbcTemplateRepository repository) {
        this.repository = repository;
    }

    // Methods
    public List<User> findAllUsers() {
        return repository.findAllUsers();
    }

    public User findById(int id) {
        return repository.findById(id);
    }

    public boolean deleteUser(int id) {
        return repository.deleteUser(id);
    }

    // Methods to validate
    public Result<User> addUser(User user) {

    }

    public Result<User> updateUser(User user) {

    }
}
