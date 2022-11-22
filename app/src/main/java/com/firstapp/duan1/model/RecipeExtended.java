package com.firstapp.duan1.model;

public class RecipeExtended {
    int mahdct, soluong, masp, mahoadon;
    String tensp;
    double tongtien;

    public RecipeExtended() {}

    public RecipeExtended(int mahdct, int soluong, int masp, int mahoadon, String tensp, double tongtien) {
        this.mahdct = mahdct;
        this.soluong = soluong;
        this.masp = masp;
        this.mahoadon = mahoadon;
        this.tensp = tensp;
        this.tongtien = tongtien;
    }
}
