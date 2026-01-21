package org.example;
public class Member extends Person {
    private String membershipType;

    public Member(int id, String name, String membershipType) {
        super(id, name);
        setmembershipType(membershipType);
    }

    public void setmembershipType(String name) {
        if (name != null && !name.isEmpty()) {
            this.membershipType = name;
        } else {
            this.membershipType = "Unknown";
        }
    }

    @Override
    public void displayInfo() {
        System.out.print("[MEMBER] ");
        super.displayInfo();
        System.out.println("Membership: " + membershipType);
    }
}