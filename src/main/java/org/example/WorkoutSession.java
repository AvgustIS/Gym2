package org.example;

public class WorkoutSession {
    private int sessionId;
    private String memberName;
    private String activity;
    private int durationMinutes;

    public WorkoutSession(int sessionId, String memberName, String activity, int durationMinutes) {
        this.sessionId = sessionId;
        this.memberName = memberName;
        this.activity = activity;
        this.durationMinutes = durationMinutes;
    }

    public WorkoutSession() {
        this.sessionId = 0;
        this.memberName = "Guest";
        this.activity = "Yoga";
        this.durationMinutes = 30;
    }

    public int getSessionId() { return sessionId; }
    public void setSessionId(int sessionId) { this.sessionId = sessionId; }

    public String getMemberName() { return memberName; }
    public void setMemberName(String memberName) { this.memberName = memberName; }

    public String getActivity() { return activity; }
    public void setActivity(String activity) { this.activity = activity; }

    public int getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(int durationMinutes) { this.durationMinutes = durationMinutes; }

    public int getCaloriesBurned() {
        return durationMinutes * 10;
    }
    public String getIntensityLevel() {
        if (durationMinutes > 60) return "High";
        return "Moderate";
    }

    @Override
    public String toString() {
        return "Session{id=" + sessionId + ", member='" + memberName + "', activity='" + activity + "', mins=" + durationMinutes + "}";
    }
}
