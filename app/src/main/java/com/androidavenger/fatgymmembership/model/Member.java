package com.androidavenger.fatgymmembership.model;

public class Member {

    private String memberName;
    private String memberLastName;
    private String memberPhone;
    private String memberType;
    private int memberId;

    public Member(String memberName, String memberLastName, String memberPhone, String memberType, int memberId) {
        this.memberName = memberName;
        this.memberLastName = memberLastName;
        this.memberPhone = memberPhone;
        this.memberType = memberType;
        this.memberId = memberId;
    }

    public Member(String memberName, String memberLastName, String memberPhone, String memberType) {
        this.memberName = memberName;
        this.memberLastName = memberLastName;
        this.memberPhone = memberPhone;
        this.memberType = memberType;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberLastName() {
        return memberLastName;
    }

    public void setMemberLastName(String memberLastName) {
        this.memberLastName = memberLastName;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }
}
