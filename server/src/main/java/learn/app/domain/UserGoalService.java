package learn.app.domain;

import learn.app.data.UserGoalRepository;
import learn.app.models.goals.UserGoal;
import org.springframework.stereotype.Service;

@Service
public class UserGoalService {

    private final UserGoalRepository repository;

    public UserGoalService(UserGoalRepository repository) {
        this.repository = repository;
    }

    public UserGoal findUserGoal(int userGoalId) {
        return repository.findUserGoalById(userGoalId);
    }

    public UserGoal addUserGoal(UserGoal userGoal) {
        return repository.addUserGoal(userGoal);
    }

    public boolean updateUserGoal(UserGoal userGoal) {
        return repository.updateUserGoal(userGoal);
    }
}
