package learn.app.models.workout;

public class WeeklyProgram {

    // Fields
    private int weeklyProgramId;
    private String programName;
    private WorkoutCategory workoutCategory;

    // Constructor
    public WeeklyProgram(int weeklyProgramId, String programName, WorkoutCategory workoutCategory) {

        this.weeklyProgramId = weeklyProgramId;
        this.programName = programName;
        this.workoutCategory = workoutCategory;
    }

    // Getters & Setters

    public int getWeeklyProgramId() {
        return weeklyProgramId;
    }

    public void setWeeklyProgramId(int weeklyProgramId) {
        this.weeklyProgramId = weeklyProgramId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public WorkoutCategory getWorkoutCategory() {
        return workoutCategory;
    }

    public void setWorkoutCategory(WorkoutCategory workoutCategory) {
        this.workoutCategory = workoutCategory;
    }
}
