package com.firstapp.duan1.firebase.controller;

import com.firstapp.duan1.firebase.Firebase;
import com.firstapp.duan1.model.ProductCategory;
import com.firstapp.duan1.model.Product;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ControllerLoaiHang extends ControllerBase<ProductCategory> {
    public ControllerLoaiHang() {
        super("table_nguoi_dung");
    }

    @Override
    public boolean setSync(ProductCategory value, boolean update) {
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
    public void setAsync(ProductCategory value, boolean update, SuccessListener successListener, FailureListener failureListener) {
        // Asynchronous function
    }

    public int removeGetResult(String id) {
        try {
            List<Product> list = new ControllerSanPham().getAllSync();
            boolean hasAny = list.stream().anyMatch(product -> product.productCategoryId.equals(id));

            if (hasAny) return 3;

            Tasks.await(Firebase.database.child(this.table).child(id).setValue(null));
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 2;
        }
    }

    @Override
    public boolean removeSync(String id) {
        // Not implemented
        return false;
    }

    @Override
    public void removeAsync(String id, SuccessListener successListener, FailureListener failureListener) {
        // Asynchronous function
    }

    @Override
    public ProductCategory getSync(String id) {
        try {
            return Tasks.await(Firebase.database.child(this.table).child(id).get()).getValue(ProductCategory.class);
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
    public List<ProductCategory> getAllSync() {
        try {
            List<ProductCategory> list = new ArrayList<>();

            for (DataSnapshot dataSnapshot : Tasks.await(Firebase.database.child(this.table).get()).getChildren()) {
                list.add(dataSnapshot.getValue(ProductCategory.class));
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
