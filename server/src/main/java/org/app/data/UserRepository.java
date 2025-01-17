package org.app.data;

import org.app.models.user.User;

import java.util.List;

public interface UserRepository {

    List<User> findAllUsers();
    User findById(int id);
    User addUser(User user);
    boolean deleteUser(User user);

}
