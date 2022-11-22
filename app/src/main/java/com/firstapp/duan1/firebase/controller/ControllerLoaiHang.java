package com.firstapp.duan1.firebase.controller;

import com.firstapp.duan1.firebase.Firebase;
import com.firstapp.duan1.model.LoaiHang;
import com.firstapp.duan1.model.LoaiHang;
import com.firstapp.duan1.model.SanPham;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ControllerLoaiHang extends ControllerBase<LoaiHang> {
    public ControllerLoaiHang() {
        super("table_nguoi_dung");
    }

    @Override
    public boolean set(LoaiHang value, boolean update) {
        DatabaseReference tableReference = Firebase.database.child(this.table);
        DatabaseReference rowReference;

        try {
            if (!update) {
                rowReference = tableReference.push();

                // override ID
                value.__id = rowReference.getKey();

                Tasks.await(Firebase.database.child(this.table).child(Objects.requireNonNull(rowReference.getKey())).setValue(value));
            } else {
                rowReference = tableReference.child(value.__id);
                Tasks.await(rowReference.setValue(value));
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void set(LoaiHang value, boolean update, SuccessListener successListener, FailureListener failureListener) {
        // Asynchronous function
    }

    public int removeGetResult(String id) {
        try {
            List<SanPham> list = new ControllerSanPham().getAll();
            boolean hasAny = list.stream().anyMatch(sanPham -> sanPham.categoryId.equals(id));

            if (hasAny) return 3;

            Tasks.await(Firebase.database.child(this.table).child(id).setValue(null));
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 2;
        }
    }

    @Override
    public boolean remove(String id) {
        // Not implemented
        return false;
    }

    @Override
    public void remove(String id, SuccessListener successListener, FailureListener failureListener) {
        // Asynchronous function
    }

    @Override
    public LoaiHang get(String id) {
        try {
            return Tasks.await(Firebase.database.child(this.table).child(id).get()).getValue(LoaiHang.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void get(String id, SuccessListener successListener, FailureListener failureListener) {
        // Asynchronous function
    }

    @Override
    public List<LoaiHang> getAll() {
        try {
            List<LoaiHang> list = new ArrayList<>();

            for (DataSnapshot dataSnapshot : Tasks.await(Firebase.database.child(this.table).get()).getChildren()) {
                list.add(dataSnapshot.getValue(LoaiHang.class));
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void getAll(SuccessListener successListener, FailureListener failureListener) {
        // Asynchronous function
    }
}
