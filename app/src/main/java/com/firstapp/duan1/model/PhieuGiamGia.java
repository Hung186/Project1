package com.firstapp.duan1.model;

public class PhieuGiamGia {
    private int maphieu;
    private double giatrigiam;
    private String ngaynhan, ngayhethan;
    private int mand;

    public int getMaphieu() {
        return maphieu;
    }

    public void setMaphieu(int maphieu) {
        this.maphieu = maphieu;
    }

    public double getGiatrigiam() {
        return giatrigiam;
    }

    public void setGiatrigiam(double giatrigiam) {
        this.giatrigiam = giatrigiam;
    }

    public String getNgaynhan() {
        return ngaynhan;
    }

    public void setNgaynhan(String ngaynhan) {
        this.ngaynhan = ngaynhan;
    }

    public String getNgayhethan() {
        return ngayhethan;
    }

    public void setNgayhethan(String ngayhethan) {
        this.ngayhethan = ngayhethan;
    }

    public int getMand() {
        return mand;
    }

    public void setMand(int mand) {
        this.mand = mand;
    }

    public PhieuGiamGia(int maphieu, double giatrigiam, String ngaynhan, String ngayhethan, int mand) {
        this.maphieu = maphieu;
        this.giatrigiam = giatrigiam;
        this.ngaynhan = ngaynhan;
        this.ngayhethan = ngayhethan;
        this.mand = mand;
    }
}