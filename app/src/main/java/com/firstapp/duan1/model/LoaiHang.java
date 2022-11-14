package com.firstapp.duan1.model;

public class LoaiHang {
    private int maloai;
    private String tenloai;

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    public LoaiHang(int maloai, String tenloai) {
        this.maloai = maloai;
        this.tenloai = tenloai;
    }
}
