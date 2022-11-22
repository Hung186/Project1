package com.firstapp.duan1.model;

public class NguoiDung {
    public String __id, googleID, facebookID, fullName, emailAddress, accountType;
    public int phoneNumber;

    public NguoiDung() {}

    public NguoiDung(String id, String googleID, String facebookID, String fullName, String emailAddress, int phoneNumber, String accountType) {
        this.__id = id;
        this.googleID = googleID;
        this.facebookID = facebookID;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.accountType = accountType;
    }
}
