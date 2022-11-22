package com.firstapp.duan1.model;

public class Coupon {
    public String __id, couponUserId, couponDateReceived, couponDateExpire;
    public double couponReductionPercentage;

    public Coupon() {}

    public Coupon(String id, String couponUserId, String couponDateReceived, String couponDateExpire, double couponReductionPercentage) {
        this.__id = id;
        this.couponUserId = couponUserId;
        this.couponDateReceived = couponDateReceived;
        this.couponDateExpire = couponDateExpire;
        this.couponReductionPercentage = couponReductionPercentage;
    }
}