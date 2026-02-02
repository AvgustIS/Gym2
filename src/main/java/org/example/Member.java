package org.example;

public class Member extends Person {
    private String membershipType;
    private static int credit = 2000;

    public Member(int id, String name, String membershipType) {
        super(id, name);
        membershipType(membershipType);
    }

    public void addmoney(int money){
        wallet+=money;
    }

    //credit=2000tg
    public void buycredit(int num){
        if (num>0 && (wallet-(credit*num)>0)){
            wallet=wallet-(credit*num);
        } else {
            throw new IllegalArgumentException("not enough remedy");
        }
    }
    public void membershipType(String membershipType) {
        if (membershipType == null || membershipType.trim().isEmpty()) {
            throw new IllegalArgumentException("Membership required.");
        }
        this.membershipType = membershipType;
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
    public String toString() {
        return super.toString() + " | membership: " + membershipType;
    }
}