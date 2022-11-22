package com.firstapp.duan1.firebase.controller;

import com.firstapp.duan1.firebase.Firebase;
import com.firstapp.duan1.model.Product;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ControllerSanPham extends ControllerBase<Product> {
    public ControllerSanPham() {
        super("table_nguoi_dung");
    }

    @Override
    public boolean setSync(Product value, boolean update) {
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
    public void setAsync(Product value, boolean update, SuccessListener successListener, FailureListener failureListener) {
        // Asynchronous function
    }

    @Override
    public boolean removeSync(String id) {
        try {
            Tasks.await(Firebase.database.child(this.table).child(id).setValue(null));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void removeAsync(String id, SuccessListener successListener, FailureListener failureListener) {
        // Asynchronous function
    }

    @Override
    public Product getSync(String id) {
        try {
            return Tasks.await(Firebase.database.child(this.table).child(id).get()).getValue(Product.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void getAsync(String id, SuccessListener successListener, FailureListener failureListener) {
        // Asynchronous function
    }

    @Override
    public List<Product> getAllSync() {
        try {
            List<Product> list = new ArrayList<>();

            for (DataSnapshot dataSnapshot : Tasks.await(Firebase.database.child(this.table).get()).getChildren()) {
                list.add(dataSnapshot.getValue(Product.class));
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void getAllAsync(SuccessListener successListener, FailureListener failureListener) {
        // Asynchronous function
    }
}
