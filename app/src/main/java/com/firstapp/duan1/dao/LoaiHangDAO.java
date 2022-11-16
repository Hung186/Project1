package com.firstapp.duan1.dao;

import android.content.ContentValues;
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

    public boolean themLoaiHang(String tenloai){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenloai", tenloai);
        long check = sqLiteDatabase.insert("LOAIHANG", null, contentValues);
        if (check == -1)
            return false;
        return true;
    }

    //1:xóa thành công - 0: xóa thất bại - -1: có sản phẩm tồn tại trong thể loại đó
    public int xoaLoaiHang(int maloai){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SANPHAM WHERE maloai = ?", new String[]{String.valueOf(maloai)});
        if (cursor.getCount() != 0){
            return -1;
        }

        long check = sqLiteDatabase.delete("LOAIHANG", "maloai=?", new String[]{String.valueOf(maloai)});
        if (check == -1)
            return 0;
        return 1;
    }

    public boolean thayDoiLoaiHang(int maloai, String tenloai){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenloai", tenloai);
        long check = sqLiteDatabase.update("LOAIHANG", contentValues, "maloai = ?", new String[]{String.valueOf(maloai)});
        if (check == -1)
            return false;
        return true;

    }
}
