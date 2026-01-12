package org.example;
public class Trainer extends Person {
    private String specialization;

    public Trainer(int id, String name, String specialization) {
        super(id, name);
        this.specialization = specialization;
    }

    @Override
    public void displayInfo() {
        System.out.print("[TRAINER] ");
        super.displayInfo();
        System.out.println("Specialization: " + specialization);
    }
}