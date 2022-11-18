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
import com.firstapp.duan1.adapter.LoaiHangAdapter;
import com.firstapp.duan1.adapter.SanPhamAdapter;
import com.firstapp.duan1.dao.LoaiHangDAO;
import com.firstapp.duan1.dao.SanPhamDAO;
import com.firstapp.duan1.model.SanPham;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class QLSanPham extends Fragment {
    Spinner spLoaiHang;
    RecyclerView recyclerQLSanPham;
    SanPhamDAO sanPhamDAO;
    ArrayList<SanPham> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ql_sanpham, container, false);
        spLoaiHang = view.findViewById(R.id.spLoaiHang);
        recyclerQLSanPham = view.findViewById(R.id.recyclerQLSanPham);

        //data
        sanPhamDAO = new SanPhamDAO(getContext());


        loadData();


        return view;
    }

    private void loadData() {
        list = sanPhamDAO.getDSSanPham();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerQLSanPham.setLayoutManager(linearLayoutManager);
        SanPhamAdapter adapter = new SanPhamAdapter(list, getContext());
        recyclerQLSanPham.setAdapter(adapter);
    }
}
