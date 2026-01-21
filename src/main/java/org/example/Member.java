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
            throw new IllegalArgumentException("Membership required.");
        }
    }

    public String getMembershipType() {
        return membershipType;
    }

    @Override
     public void aboniment() {
        System.out.print("[MEMBER] ");
        super.displayInfo();
        System.out.println("Membership: " + membershipType);
    }
}