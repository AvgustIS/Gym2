package org.example;

public class Trainer extends Person  {
    private String specialization;
    private int sallary;



    public Trainer(int id, String name, String specialization, double sallary) {
        super(id, name);
        setSpecialization(specialization);
        setSallary(sallary);
    }

    public void setSallary(double sallary) {
        if (sallary>0){
            this.specialization = specialization;
        } else {
            throw new IllegalArgumentException("Sallery can't be enough");
        }
    }

    public void setSpecialization(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Specialization required.");
        }
        this.specialization = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public int getSallary() {
        return sallary;
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
    public String toString() {
        return super.toString() + " | Specialization: " + specialization;
    }
}