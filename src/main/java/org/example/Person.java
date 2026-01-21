package org.example;
public abstract class Person {
    protected int id;
    protected String name;

    abstract void aboniment();

    public Person(int id, String name) {
        setId(id);
        setName(name);
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        } else {
            this.id = 000;
        }
    }

    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else {
            this.name = "Unknown";
        }
    }

    public String getName() {
        return name;
    }

    public void displayInfo() {
        System.out.println("ID: " + id + " | Name: " + name);
    }
}