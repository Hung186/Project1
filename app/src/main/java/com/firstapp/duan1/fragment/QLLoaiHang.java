package com.firstapp.duan1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firstapp.duan1.R;
import com.firstapp.duan1.adapter.LoaiHangAdapter;
import com.firstapp.duan1.dao.LoaiHangDAO;
import com.firstapp.duan1.model.LoaiHang;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class QLLoaiHang extends Fragment {
    LoaiHangDAO loaiHangDAO;
    RecyclerView recyclerLoaiHang;
    ArrayList<LoaiHang> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ql_loaihang, container, false);

        recyclerLoaiHang = view.findViewById(R.id.recyclerQLLoaiHang);
        FloatingActionButton floatAdd = view.findViewById(R.id.floatAdd);

        //data
        loaiHangDAO = new LoaiHangDAO(getContext());


        loadData();

        return view;
    }

    private void loadData() {
        list = loaiHangDAO.getDSLoaiHang();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerLoaiHang.setLayoutManager(linearLayoutManager);
        LoaiHangAdapter adapter = new LoaiHangAdapter(list, getContext());
        recyclerLoaiHang.setAdapter(adapter);
    }
}
