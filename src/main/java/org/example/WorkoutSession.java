package org.example;
public class WorkoutSession {
    private String workoutName;
    private int Minutes;
    protected int credits;

    public WorkoutSession(String workoutName, int Minutes,int credits) {
        setWorkoutName(workoutName);
        setMinutes(Minutes);
        setCredits(credits);
    }

    public void setWorkoutName(String workoutName) {
        if (workoutName != null && !workoutName.isEmpty()) {
            this.workoutName = workoutName;
        } else {
            throw new IllegalArgumentException("WorkName cannot be empty.");
        }
    }

    public void setMinutes(int Minutes) {
        if (Minutes > 0) {
            this.Minutes = Minutes;
        } else {
            throw new IllegalArgumentException("The workout cannot be less than 30 minutes.");
        }
    }
    public void setCredits(int credits){
        if (credits>=0){
            this.credits = credits;
        }
        else {
            throw new IllegalArgumentException("the credit must be greater than 0.");
        }

    }

    public int getCredits() {
        return credits;
    }

    public void showSessions() {
        System.out.println("Workout: " + workoutName + " (" + Minutes + " mins)" + "Credit" + credits);
    }
}