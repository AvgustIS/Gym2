package org.example;
public class Trainer extends Person {
    private String specialization;

    public Trainer(int id, String name, String specialization) {
        super(id, name);
        setSpecialization(specialization);
    }

    public void setSpecialization(String name) {
        if (name != null && !name.isEmpty()) {
            this.specialization = name;
        } else {
            this.specialization = "Unknown";
        }
    }

    public String getSpecialization() {
        return specialization;
    }

    @Override
    public void displayInfo() {
        System.out.print("[TRAINER] ");
        super.displayInfo();
        System.out.println("Specialization: " + specialization);
    }
}