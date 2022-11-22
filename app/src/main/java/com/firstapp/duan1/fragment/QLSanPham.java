package com.firstapp.duan1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firstapp.duan1.R;
import com.firstapp.duan1.adapter.ProductAdapter;
import com.firstapp.duan1.firebase.controller.ControllerSanPham;
import com.firstapp.duan1.model.Product;

import java.util.List;

public class QLSanPham extends Fragment {
    private Spinner spLoaiHang;
    private RecyclerView recyclerQLSanPham;
    private List<Product> list;
    private final ControllerSanPham controllerSanPham = new ControllerSanPham();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ql_sanpham, container, false);
        spLoaiHang = view.findViewById(R.id.spLoaiHang);
        recyclerQLSanPham = view.findViewById(R.id.recyclerQLSanPham);

        loadData();

        return view;
    }



    private void loadData() {
        list = controllerSanPham.getAllSync();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        recyclerQLSanPham.setLayoutManager(linearLayoutManager);

        ProductAdapter adapter = new ProductAdapter(list, getContext());

        recyclerQLSanPham.setAdapter(adapter);
    }

}
