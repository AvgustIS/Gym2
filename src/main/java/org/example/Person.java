package org.example;

public abstract class Person {
    protected int id;
    protected String name;
    protected String phone;
    protected int age;

    public Person(int id, String name, String phone, int age) {
        setId(id);
        setName(name);
        setPhone(phone);
        setAge(age);
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public int getAge() { return age; }

    public void setId(int id) {
        if (id < 0) throw new IllegalArgumentException("ID cannot be negative");
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Name cannot be empty");
        this.name = name.trim();
    }

    public void setPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) throw new IllegalArgumentException("Phone cannot be empty");
        this.phone = phone.trim();
    }

    public void setAge(int age) {
        if (age < 0) throw new IllegalArgumentException("Age cannot be negative");
        this.age = age;
    }

    // Абстрактные методы — заставляют детей реализовать поведение (Week 6).
    public abstract String getRole();
    public abstract void act();

    @Override
    public String toString() {
        return "[" + getRole() + "] " + name + " (ID: " + id + ", phone: " + phone + ", age: " + age + ")";
    }
}
