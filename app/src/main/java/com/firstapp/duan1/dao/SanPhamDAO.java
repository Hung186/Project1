package com.firstapp.duan1.dao;

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
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT sp.masach, sp.tensach, sp.giathue, sp.maloai, lo.tenloai FROM SANPHAM sp, LOAIHANG lo WHERE sp.maloai = lo.maloai", null);

        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new SanPham(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4), cursor.getDouble(5), cursor.getBlob(6)));
            } while (cursor.moveToNext());
        }
        return list;
    }
}
