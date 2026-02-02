package org.example;


public class Trainer extends Person implements Trainable {
    private String specialization;
    private double salary;

    public Trainer(int id, String name, String phone, int age, String specialization, double salary) {
        super(id, name, phone, age);
        setSpecialization(specialization);
        setSalary(salary);
    }

    public String getSpecialization() {
        return specialization;
    }

    public double getSalary() {
        return salary;
    }

    public void setSpecialization(String specialization) {
        if (specialization == null || specialization.trim().isEmpty())
            throw new IllegalArgumentException("Specialization cannot be empty");
        this.specialization = specialization.trim();
    }

    public void setSalary(double salary) {
        if (salary < 0) throw new IllegalArgumentException("Salary cannot be negative");
        this.salary = salary;
    }

    @Override
    public String getRole() {
        return "Trainer";
    }

    @Override
    public void act() {
        System.out.println("Trainer " + getName() + " is coaching (" + specialization + ").");
    }

    @Override
    public void train() {
        System.out.println("Trainer " + getName() + " conducts a training session.");
    }

    @Override
    public String toString() {
        return super.toString() + " | Spec: " + specialization + " | Salary: " + salary;
    }
}
