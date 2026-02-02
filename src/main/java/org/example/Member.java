package org.example;


public class Member extends Person {
    private String membershipType;
    private double monthlyFee;

    public Member(int id, String name, String phone, int age, String membershipType, double monthlyFee) {
        super(id, name, phone, age);
        setMembershipType(membershipType);
        setMonthlyFee(monthlyFee);
    }

    public String getMembershipType() { return membershipType; }
    public double getMonthlyFee() { return monthlyFee; }

    public void setMembershipType(String membershipType) {
        if (membershipType == null || membershipType.trim().isEmpty())
            throw new IllegalArgumentException("Membership type cannot be empty");
        this.membershipType = membershipType.trim();
    }

    public void setMonthlyFee(double monthlyFee) {
        if (monthlyFee < 0) throw new IllegalArgumentException("Monthly fee cannot be negative");
        this.monthlyFee = monthlyFee;
    }

    @Override
    public String getRole() { return "Member"; }

    @Override
    public void act() {
        System.out.println(name + " is training (" + membershipType + ").");
    }

    @Override
    public String toString() {
        return super.toString() + " | Membership: " + membershipType + " | Fee: " + monthlyFee;
    }
}
