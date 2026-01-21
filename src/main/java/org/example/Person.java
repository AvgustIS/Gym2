package org.example;
public abstract class Person implements GymAction {
    protected int id;
    protected String name;

    public abstract void aboniment();

    public Person(int id, String name) {
        setId(id);
        setName(name);
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("ID must be positive.");
        }
    }

    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public void performAction() {
        System.out.println(name + " is performing a gym activity.");
    }

    public void displayInfo() {
        System.out.println("ID: " + id + " | Name: " + name);
    }
}