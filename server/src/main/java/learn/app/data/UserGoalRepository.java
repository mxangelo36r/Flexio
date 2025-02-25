package learn.app.data;

import learn.app.models.goals.UserGoal;

public interface UserGoalRepository {

    UserGoal findUserGoal(int userGoalId);
    UserGoal addUserGoal(UserGoal userGoal);
    boolean updateUserGoal(UserGoal userGoal);
}
