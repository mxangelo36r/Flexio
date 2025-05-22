package learn.app.models.goals;

import com.fasterxml.jackson.annotation.JsonFormat;
import learn.app.models.user.User;
import org.apache.tomcat.jni.Local;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class UserGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userGoalId;
    private int userId;
    @NotNull(message = "You must select a valid goal type")
    private GoalType goalType;
    private double targetWeight;
    @Min(value = 0, message = "Weekly visits cannot be negative")
    @NotNull(message = "Weekly visits cannot be empty")
    private int weeklyVisits;
    @NotNull(message = "Start date cannot be empty")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @NotNull(message = "End date cannot be empty")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    public UserGoal(int userGoalId, int userId, GoalType goalType, double targetWeight, int weeklyVisits, LocalDate startDate, LocalDate endDate) {
        this.userGoalId = userGoalId;
        this.userId = userId;
        this.goalType = goalType;
        this.targetWeight = targetWeight;
        this.weeklyVisits = weeklyVisits;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public UserGoal() {

    }

    public int getUserGoalId() {
        return userGoalId;
    }

    public void setUserGoalId(int userGoalId) {
        this.userGoalId = userGoalId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public GoalType getGoalType() {
        return goalType;
    }

    public void setGoalType(GoalType goalType) {
        this.goalType = goalType;
    }

    public double getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(double targetWeight) {
        this.targetWeight = targetWeight;
    }

    public int getWeeklyVisits() {
        return weeklyVisits;
    }

    public void setWeeklyVisits(int weeklyVisits) {
        this.weeklyVisits = weeklyVisits;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
