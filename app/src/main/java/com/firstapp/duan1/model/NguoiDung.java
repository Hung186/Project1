package com.firstapp.duan1.model;

public class NguoiDung {
    private int mand;
    private String tennd, email, sdt, matkhau, loaitaikhoan;

    public NguoiDung(int mand, String tennd, String email, String sdt, String matkhau, String loaitaikhoan) {
        this.mand = mand;
        this.tennd = tennd;
        this.email = email;
        this.sdt = sdt;
        this.matkhau = matkhau;
        this.loaitaikhoan = loaitaikhoan;
    }

    public int getMand() {
        return mand;
    }

    public void setMand(int mand) {
        this.mand = mand;
    }

    public String getTennd() {
        return tennd;
    }

    public void setTennd(String tennd) {
        this.tennd = tennd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getLoaitaikhoan() {
        return loaitaikhoan;
    }

    public void setLoaitaikhoan(String loaitaikhoan) {
        this.loaitaikhoan = loaitaikhoan;
    }
}
