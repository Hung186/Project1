package com.firstapp.duan1.model;

public class HoaDon {
    public String __id, userId, status, date;
    public double totalCost;

    public HoaDon() {}

    public HoaDon(String id, String userId, String status, String date, double totalCost) {
        this.__id = id;
        this.userId = userId;
        this.status = status;
        this.date = date;
        this.totalCost = totalCost;
    }
}
