package com.firstapp.duan1.model;

public class PhieuGiamGia {
    private int maphieu, mahd;
    private String ngaynhan, ngayhethan;
    private double giatrigiam;

    public PhieuGiamGia(int maphieu, int mahd, String ngaynhan, String ngayhethan, double giatrigiam) {
        this.maphieu = maphieu;
        this.mahd = mahd;
        this.ngaynhan = ngaynhan;
        this.ngayhethan = ngayhethan;
        this.giatrigiam = giatrigiam;
    }

    public int getMaphieu() {
        return maphieu;
    }

    public void setMaphieu(int maphieu) {
        this.maphieu = maphieu;
    }

    public int getMahd() {
        return mahd;
    }

    public void setMahd(int mahd) {
        this.mahd = mahd;
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

    public double getGiatrigiam() {
        return giatrigiam;
    }

    public void setGiatrigiam(double giatrigiam) {
        this.giatrigiam = giatrigiam;
    }
}
