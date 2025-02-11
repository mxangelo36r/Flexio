package learn.app.data;

import learn.app.models.user.User;

import java.util.List;

public interface UserRepository {

    List<User> findAllUsers();
    User findById(int id);
    User addUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(int id);

}
