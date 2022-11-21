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
import com.firstapp.duan1.adapter.KhachHangAdapter;
import com.firstapp.duan1.adapter.LoaiHangAdapter;
import com.firstapp.duan1.dao.NguoiDungDAO;
import com.firstapp.duan1.model.LoaiHang;
import com.firstapp.duan1.model.NguoiDung;

import java.util.ArrayList;

public class QLKhachHang extends Fragment {
    private ArrayList<NguoiDung> list;
    private NguoiDungDAO nguoiDungDAO;
    private RecyclerView recyclerQLKhachHang;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ql_khachhang, container, false);

        recyclerQLKhachHang = view.findViewById(R.id.recyclerQLKhachHang);

        nguoiDungDAO = new NguoiDungDAO(getContext());

        loadData();


        return view;
    }

    private void loadData() {
        list = nguoiDungDAO.getDSKhachHang();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerQLKhachHang.setLayoutManager(linearLayoutManager);
        KhachHangAdapter adapter = new KhachHangAdapter(list, getContext());
        recyclerQLKhachHang.setAdapter(adapter);
    }
}
