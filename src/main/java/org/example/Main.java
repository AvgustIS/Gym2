package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println(" GYM MANAGEMENT SYSTEM ");
        System.out.println();

        Member m1 = new Member(101, "Alice", 65, "Basic");
        Member m2 = new Member(112, "Isaali",17,"Basic");
        Trainer t1 = new Trainer(501, "Coach Bob", "Crossfit", 10);
        WorkoutSession s1 = new WorkoutSession(901, "Alice", "Cardio", 45);
        WorkoutSession s2 = new WorkoutSession();

        System.out.println(" INFORMATION  ");
        System.out.println(m1);
        System.out.println(m2);
        System.out.println(t1);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println();

        System.out.println(" INFORMATION (about) ");
        System.out.println(m1.getName() + " is senior: " + m1.isSenior());
        m1.applyUpgrade("Premium");
        System.out.println("Updated m1: " + m1);

        System.out.println(t1.getName() + " is expert: " + t1.isExpert());
        System.out.println("Bonus for " + t1.getName() + ": $" + t1.getPerformanceBonus());

        System.out.println("Session 1 calories: " + s1.getCaloriesBurned());
        System.out.println("Session 2 intensity: " + s2.getIntensityLevel());
    }
}
