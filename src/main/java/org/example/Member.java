package org.example;
public class Member extends Person {
    private String membershipType;

    public Member(int id, String name, String membershipType) {
        super(id, name);
        this.membershipType = membershipType;
    }

    @Override
    public void displayInfo() {
        System.out.print("[MEMBER] ");
        super.displayInfo();
        System.out.println("Membership: " + membershipType);
    }
}