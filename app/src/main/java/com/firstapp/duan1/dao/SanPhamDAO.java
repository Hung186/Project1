package com.firstapp.duan1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.firstapp.duan1.database.DbHelper;
import com.firstapp.duan1.model.SanPham;

import java.util.ArrayList;

public class SanPhamDAO {

    DbHelper dbHelper;

    public SanPhamDAO(Context context) {
        dbHelper = new DbHelper(context);
    }
    public ArrayList<SanPham> getDSSanPham(){
        ArrayList<SanPham> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT sp.masp, sp.tensp, sp.thuonghieu, sp.giasp, sp.hinhanh, sp.maloai, sp.motasanpham, lo.tenloai FROM SANPHAM sp, LOAIHANG lo WHERE sp.maloai = lo.maloai", null);

        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                //int masp, String tensp, String thuonghieu, double giasp, String hinhanh, String motasanpham, int maloai
                list.add(new SanPham(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getDouble(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public boolean themSanPham(SanPham sanPham){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //masp integer primary key autoincrement, tensp text, thuonghieu text, giasp real, hinhanh blob, maloai integer references LOAIHANG(maloai)
        contentValues.put("tensp", sanPham.getTensp());
        contentValues.put("thuonghieu", sanPham.getThuonghieu());
        contentValues.put("giasp", sanPham.getGiasp());
        contentValues.put("hinhanh", sanPham.getHinhanh());
        contentValues.put("maloai", sanPham.getMaloai());
        long check = sqLiteDatabase.insert("SANPHAM", null, contentValues);
        if (check == -1)
            return false;
        return true;
    }


    public boolean capNhatSanPham(SanPham sanPham){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tensp", sanPham.getTensp());
        contentValues.put("thuonghieu", sanPham.getThuonghieu());
        contentValues.put("giasp", sanPham.getGiasp());
        contentValues.put("hinhanh", sanPham.getHinhanh());
        contentValues.put("maloai", sanPham.getMaloai());
        long check = sqLiteDatabase.update("SANPHAM", contentValues, "masp=?", new String[]{String.valueOf(sanPham.getMasp())});
        if (check==-1)
            return false;
        return true;
    }

    //1:xóa thành công - 0: xóa thất bại - -1: có sản phẩm tồn tại trong thể loại đó
    public int xoaSanPham(int masp){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM HOADONCHITIET WHERE masp = ?", new String[]{String.valueOf(masp)});
        if (cursor.getCount() != 0){
            return -1;
        }

        long check = sqLiteDatabase.delete("SANPHAM", "masp = ?", new String[]{String.valueOf(masp)});
        if (check == -1)
            return 0;
        return 1;
    }

}
