package com.firstapp.duan1.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firstapp.duan1.R;
import com.firstapp.duan1.adapter.ProductCategoryAdapter;
import com.firstapp.duan1.firebase.controller.ControllerLoaiHang;
import com.firstapp.duan1.model.ProductCategory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class QLLoaiHang extends Fragment {
    private RecyclerView recyclerLoaiHang;
    private List<ProductCategory> list;
    private final ControllerLoaiHang controllerLoaiHang = new ControllerLoaiHang();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ql_loaihang, container, false);

        recyclerLoaiHang = view.findViewById(R.id.recyclerQLLoaiHang);
        FloatingActionButton floatAdd = view.findViewById(R.id.floatAdd);


        loadData();
        floatAdd.setOnClickListener(v -> showDialog());

        return view;
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_them_loaihang, null);
        builder.setView(view);

        EditText etCategoryName = view.findViewById(R.id.edtTenLoai);

        builder.setNegativeButton("Thêm", (dialog, which) -> {
            String name = etCategoryName.getText().toString();
            if (name.equals("")){
                Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
            }
            else {

                boolean check = controllerLoaiHang.setSync(new ProductCategory(null, name), false);
                if (check) {
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    list = controllerLoaiHang.getAllSync();
                    loadData();
                } else {
                    Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setPositiveButton("Hủy", (dialog, which) -> {});
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    private void loadData() {
        list = controllerLoaiHang.getAllSync();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        recyclerLoaiHang.setLayoutManager(linearLayoutManager);

        ProductCategoryAdapter adapter = new ProductCategoryAdapter(list, getContext(), controllerLoaiHang);

        recyclerLoaiHang.setAdapter(adapter);
    }
}
