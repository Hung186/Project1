package com.firstapp.duan1.model;

public class SanPham {
    private int masp, maloai, maphieu, hinhanh;
    private String tensp, thuonghieu;
    private double giasp;

    public SanPham(int masp, int maloai, int maphieu, int hinhanh, String tensp, String thuonghieu, double giasp) {
        this.masp = masp;
        this.maloai = maloai;
        this.maphieu = maphieu;
        this.hinhanh = hinhanh;
        this.tensp = tensp;
        this.thuonghieu = thuonghieu;
        this.giasp = giasp;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }

    public int getMaphieu() {
        return maphieu;
    }

    public void setMaphieu(int maphieu) {
        this.maphieu = maphieu;
    }

    public int getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(int hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getThuonghieu() {
        return thuonghieu;
    }

    public void setThuonghieu(String thuonghieu) {
        this.thuonghieu = thuonghieu;
    }

    public double getGiasp() {
        return giasp;
    }

    public void setGiasp(double giasp) {
        this.giasp = giasp;
    }
}
