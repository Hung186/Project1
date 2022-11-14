package com.firstapp.duan1.model;

public class HoaDon {
    private int mahoadon, mand;
    private String trangthai, ngay;
    private double thanhtien;


    public HoaDon(int mahoadon, int mand, String trangthai, String ngay, double thanhtien) {
        this.mahoadon = mahoadon;
        this.mand = mand;
        this.trangthai = trangthai;
        this.ngay = ngay;
        this.thanhtien = thanhtien;
    }

    public int getMahoadon() {
        return mahoadon;
    }

    public void setMahoadon(int mahoadon) {
        this.mahoadon = mahoadon;
    }

    public int getMand() {
        return mand;
    }

    public void setMand(int mand) {
        this.mand = mand;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public double getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(double thanhtien) {
        this.thanhtien = thanhtien;
    }
}
