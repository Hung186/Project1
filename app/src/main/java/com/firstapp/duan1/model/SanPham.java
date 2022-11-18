package com.firstapp.duan1.model;

public class SanPham {
    private int masp, maloai;
    private String tensp, thuonghieu;
    private double giasp;
    private String hinhanh;
    private String motasanpham;

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

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getMotasanpham() {
        return motasanpham;
    }

    public void setMotasanpham(String motasanpham) {
        this.motasanpham = motasanpham;
    }

    public SanPham(int masp, String tensp, String thuonghieu, double giasp, String hinhanh, String motasanpham, int maloai) {
        this.masp = masp;
        this.maloai = maloai;
        this.tensp = tensp;
        this.thuonghieu = thuonghieu;
        this.giasp = giasp;
        this.hinhanh = hinhanh;
        this.motasanpham = motasanpham;
    }
}
