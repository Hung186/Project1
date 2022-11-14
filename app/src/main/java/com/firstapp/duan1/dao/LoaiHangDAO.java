package com.firstapp.duan1.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.firstapp.duan1.database.DbHelper;
import com.firstapp.duan1.model.LoaiHang;

import java.util.ArrayList;

public class LoaiHangDAO {
    DbHelper dbHelper;
    public LoaiHangDAO(Context context){
        dbHelper = new DbHelper(context);
    }

    public ArrayList<LoaiHang> getDSLoaiHang(){
        ArrayList<LoaiHang> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LOAIHANG", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new LoaiHang(cursor.getInt(0), cursor.getString(1)));
            }while (cursor.moveToNext());
        }
        return list;
    }
}
