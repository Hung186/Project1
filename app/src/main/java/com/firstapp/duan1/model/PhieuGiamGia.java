package com.firstapp.duan1.model;

public class PhieuGiamGia {
    public String __id, userId, dateReceived, dateExpire;
    public double percentage;

    public PhieuGiamGia() {}

    public PhieuGiamGia(String id, String userId, String dateReceived, String dateExpire, double percentage) {
        this.__id = id;
        this.userId = userId;
        this.dateReceived = dateReceived;
        this.dateExpire = dateExpire;
        this.percentage = percentage;
    }
}