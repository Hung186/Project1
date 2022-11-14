package com.firstapp.duan1.model;

public class HoaDonChiTiet {
    private int mahdct, soluong, masp, mahoadon;
    private String tensp;
    private double tongtien;

    public int getMahdct() {
        return mahdct;
    }

    public void setMahdct(int mahdct) {
        this.mahdct = mahdct;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public int getMahoadon() {
        return mahoadon;
    }

    public void setMahoadon(int mahoadon) {
        this.mahoadon = mahoadon;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public double getTongtien() {
        return tongtien;
    }

    public void setTongtien(double tongtien) {
        this.tongtien = tongtien;
    }

    public HoaDonChiTiet(int mahdct, int soluong, int masp, int mahoadon, String tensp, double tongtien) {
        this.mahdct = mahdct;
        this.soluong = soluong;
        this.masp = masp;
        this.mahoadon = mahoadon;
        this.tensp = tensp;
        this.tongtien = tongtien;
    }
}
