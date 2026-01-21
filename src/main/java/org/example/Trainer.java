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
            throw new IllegalArgumentException("Specialization required.");
        }
    }

    public String getSpecialization() {
        return specialization;
    }

    @Override
    public void aboniment(){
        System.out.print("[TRAINER] ");
        super.displayInfo();
        System.out.println("Trainer: " + specialization);
    }

    @Override
    public void performAction() {
        System.out.println("Trainer " + name + " is coaching a session.");
    }
}