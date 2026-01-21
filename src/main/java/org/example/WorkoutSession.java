package org.example;
public class WorkoutSession {
    private String workoutName;
    private int Minutes;

    public WorkoutSession(String workoutName, int Minutes) {
        this.workoutName = workoutName;
        setMinutes(Minutes);
    }

    public void setMinutes(int Minutes) {
        if (Minutes > 0) {
            this.Minutes = Minutes;
        } else {
            throw new IllegalArgumentException("workout isn't done.");
        }
    }

    public void showSession() {
        System.out.println("Workout: " + workoutName + " (" + Minutes + " mins)");
    }
}