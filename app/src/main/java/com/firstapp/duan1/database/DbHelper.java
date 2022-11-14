package com.firstapp.duan1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context){
        super(context, "SHOPBANHANG", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String dbLoaiHang = "CREATE TABLE LOAIHANG(maloai integer primary key autoincrement, tenloai text)";
        db.execSQL(dbLoaiHang);

        String dbSanPham = "CREATE TABLE SANPHAM(masp integer primary key autoincrement, tensp text, thuonghieu text, giasp real, hinhanh integer, maloai integer references LOAIHANG(maloai))";
        db.execSQL(dbSanPham);

        String dbPhieuGiamGia = "CREATE TABLE PHIEUGIAMGIA(maphieu integer primary key autoincrement, giatrigiam real, ngaynhan text, ngayhethan text, mahoadon integer references HOADON(mahoadon))";
        db.execSQL(dbPhieuGiamGia);

        String dbNguoiDung = "CREATE TABLE NGUOIDUNG(mand integer primary key autoincrement, tennd text, email text, sdt text, matkhau text, loaitaikhoan text)";
        db.execSQL(dbNguoiDung);

        String dbHoaDon = "CREATE TABLE HOADON(mahoadon integer primary key autoincrement, trangthai text, ngay text, thanhtien real, mand integer references NGUOIDUNG(mand))";
        db.execSQL(dbHoaDon);

        String dbHoaDonChiTiet = "CREATE TABLE HOADONCHITIET(mahdct integer primary key autoincrement, tensp text, soluong integer, tongtien real, masp integer references SANPHAM(masp), mahoadon integer references HOADON(mahoadon))";
        db.execSQL(dbHoaDonChiTiet);

        db.execSQL("INSERT INTO LOAIHANG VALUES (1, 'Đồng hồ'), (2, 'Nhẫn'), (3, 'Dây chuyền'), (4, 'Vòng tay')");
        db.execSQL("INSERT INTO NGUOIDUNG VALUES (1, 'Admin123', 'nguyenvana123@gmail.com', '0934234244', '12345678', 'Admin'), (2, 'Customer123', 'nguyenvana123@gmail.com', '0934234244', '87654321', 'Customer')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS LOAIHANG");
            db.execSQL("DROP TABLE IF EXISTS SANPHAM");
            db.execSQL("DROP TABLE IF EXISTS PHIEUGIAMGIA");
            db.execSQL("DROP TABLE IF EXISTS NGUOIDUNG");
            db.execSQL("DROP TABLE IF EXISTS HOADON");
            db.execSQL("DROP TABLE IF EXISTS HOADONCHITIET");
            onCreate(db);
        }
    }
}
