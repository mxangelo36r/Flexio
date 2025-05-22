package learn.app.data;

import learn.app.models.goals.UserGoal;

public interface UserGoalRepository {

    UserGoal findById(int id);
    UserGoal addUserGoal(UserGoal userGoal);
    boolean updateUserGoal(UserGoal userGoal);
}
