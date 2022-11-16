package com.firstapp.duan1.model;

public class SanPham {
    private int masp, maloai, maphieu;
    private String tensp, thuonghieu;
    private double giasp;
    private byte[] hinhanh;

    public SanPham(int masp, int maloai, int maphieu, String tensp, String thuonghieu, double giasp, byte[] hinhanh) {
        this.masp = masp;
        this.maloai = maloai;
        this.maphieu = maphieu;
        this.tensp = tensp;
        this.thuonghieu = thuonghieu;
        this.giasp = giasp;
        this.hinhanh = hinhanh;
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

    public byte[] getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(byte[] hinhanh) {
        this.hinhanh = hinhanh;
    }
}
