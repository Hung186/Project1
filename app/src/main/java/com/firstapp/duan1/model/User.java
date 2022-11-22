package com.firstapp.duan1.model;

public class User {
    public String __id, __googleID, __facebookID, userFullName, userEmailAddress, userAvatarUrl, userAccountType;
    public int userPhoneNumber;

    public User() {}

    public User(String id, String googleID, String facebookID, String userFullName, String userEmailAddress, String userAvatarUrl, int userPhoneNumber, String userAccountType) {
        this.__id = id;
        this.__googleID = googleID;
        this.__facebookID = facebookID;
        this.userFullName = userFullName;
        this.userEmailAddress = userEmailAddress;
        this.userAvatarUrl = userAvatarUrl;
        this.userPhoneNumber = userPhoneNumber;
        this.userAccountType = userAccountType;
    }
}
