package learn.app.domain;

import learn.app.domain.result.Result;
import learn.app.domain.result.ResultType;
import learn.app.models.user.User;
import learn.app.data.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
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

        Result<User> result = validate(user);

        if (!result.isSuccess()) {
            return result;
        }

        User u = repository.addUser(user);
        result.setPayload(u);

        return result;
    }

    public Result<User> updateUser(User user) {

        Result<User> result = validate(user);

        if (!result.isSuccess()) {
            return result;
        }
        

        if (!repository.updateUser(user)) {
            String msg = String.format("User ID (%s) cannot be found", user.getUserId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    // Validations
    private Result<User> validate(User user) {

        Result<User> result = new Result<>();
        List<User> all = findAllUsers();

        if (user == null) {
            result.addMessage("User cannot be empty", ResultType.INVALID);
            return result;
        }

        if (user.getUsername() == null || user.getUsername().isEmpty() || user.getUsername().isBlank()) {
            result.addMessage("Username cannot be empty", ResultType.INVALID);
            return result;
        }

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            result.addMessage("Email cannot be empty", ResultType.INVALID);
            return result;
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            result.addMessage("Password cannot be empty", ResultType.INVALID);
            return result;
        }

        if (user.getUsername().isEmpty() && user.getEmail().isEmpty() && user.getPassword().isEmpty()) {
            result.addMessage("Username, Email and Password cannot be empty", ResultType.INVALID);
            return result;
        }

        if (user.getUsername().isEmpty() && user.getEmail().isEmpty()) {
            result.addMessage("Username and Email cannot be empty", ResultType.INVALID);
            return result;
        }

        if (user.getUsername().isEmpty() && user.getPassword().isEmpty()) {
            result.addMessage("Username and Password cannot be empty", ResultType.INVALID);
            return result;
        }

        if (user.getEmail().isEmpty() && user.getPassword().isEmpty()) {
            result.addMessage("Email and Pasword cannot be empty", ResultType.INVALID);
            return result;
        }

        if (!distinctUsername(all, user)) {
            result.addMessage("Username taken. Please choose another username", ResultType.INVALID);
            return result;
        }


        if (!distinctEmail(all, user)) {
            result.addMessage("Email taken. Please choose another email", ResultType.INVALID);
            return result;
        }

        if (user.getWeight() <= 0) {
            result.addMessage("Weight cannot be 0 or negative", ResultType.INVALID);
            return result;
        }

        if (user.getHeightFt() <= 0) {
            result.addMessage("Height in Ft cannot be 0 or negative", ResultType.INVALID);
            return result;
        }

        if (user.getHeightIn() <= 0) {
            result.addMessage("Height in In cannot be 0 or negative", ResultType.INVALID);
            return result;
        }

        return result;
    }

    // Helper Validation Methods

    private boolean distinctUsername(List<User> users, User user) {
        return users.stream()
                .noneMatch(u -> u.getUsername().equals(user.getUsername()));
    }

    private boolean distinctEmail(List<User> users, User user) {
        return users.stream()
                .noneMatch(u -> u.getEmail().equals(user.getEmail()));
    }

    private boolean containsEmail(User user) {
        return user.getEmail().contains("@") && user.getEmail().contains(".com") ||
                user.getEmail().contains("@") && user.getEmail().contains(".co") ||
                user.getEmail().contains("@") && user.getEmail().contains(".net") ||
                user.getEmail().contains("@") && user.getEmail().contains(".org");
    }

    private boolean hasNoSpaces(String str) {
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(str);
        return !matcher.find();
    }
}
