package com.firstapp.duan1.dao;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.firstapp.duan1.database.DbHelper;
import com.firstapp.duan1.model.LoaiHang;
import com.firstapp.duan1.model.NguoiDung;

import java.util.ArrayList;

public class NguoiDungDAO {
    DbHelper dbHelper;
    SharedPreferences sharedPreferences;
    public NguoiDungDAO(Context context){
        dbHelper = new DbHelper(context);
        sharedPreferences = context.getSharedPreferences("THONGTINNGUOIDUNG", MODE_PRIVATE);
    }

    public ArrayList<NguoiDung> getDSKhachHang(){
        ArrayList<NguoiDung> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM NGUOIDUNG WHERE loaitaikhoan = 'Customer'", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                //int mand, String tennd, String email, String sdt, String matkhau, String loaitaikhoan
                list.add(new NguoiDung(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public boolean checkDangNhap(String tennd, String matkhau){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM NGUOIDUNG WHERE tennd = ? AND matkhau = ?", new String[]{tennd, matkhau});
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("mand", cursor.getInt(0));
            editor.putString("tennd", cursor.getString(1));
            editor.putString("email", cursor.getString(2));
            editor.putString("sdt", cursor.getString(3));
            editor.putString("matkhau", cursor.getString(4));
            editor.putString("loaitaikhoan", cursor.getString(5));
            editor.commit();

            return true;
        }
        else {
            return false;
        }
    }
}
