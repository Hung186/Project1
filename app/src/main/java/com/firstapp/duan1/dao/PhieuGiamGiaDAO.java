package com.firstapp.duan1.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.firstapp.duan1.database.DbHelper;
import com.firstapp.duan1.model.LoaiHang;
import com.firstapp.duan1.model.PhieuGiamGia;

import java.util.ArrayList;

public class PhieuGiamGiaDAO {
    DbHelper dbHelper;

    public PhieuGiamGiaDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public ArrayList<PhieuGiamGia> getDSPhieuGiamGia(){
        ArrayList<PhieuGiamGia> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM PHIEUGIAMGIA", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                //int maphieu, double giatrigiam, String ngaynhan, String ngayhethan, int mand
                list.add(new PhieuGiamGia(cursor.getInt(0), cursor.getDouble(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4)));
            }while (cursor.moveToNext());
        }
        return list;
    }
}
