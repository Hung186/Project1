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
import com.firstapp.duan1.adapter.NguoiDungAdapter;
import com.firstapp.duan1.firebase.controller.ControllerNguoiDung;
import com.firstapp.duan1.model.NguoiDung;

import java.util.List;

public class QLKhachHang extends Fragment {
    private RecyclerView recyclerQLKhachHang;
    private final ControllerNguoiDung controllerNguoiDung = new ControllerNguoiDung();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ql_khachhang, container, false);

        recyclerQLKhachHang = view.findViewById(R.id.recyclerQLKhachHang);

        loadData();


        return view;
    }

    private void loadData() {
        List<NguoiDung> list = controllerNguoiDung.getAll();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        recyclerQLKhachHang.setLayoutManager(linearLayoutManager);
        NguoiDungAdapter adapter = new NguoiDungAdapter(list, getContext());

        recyclerQLKhachHang.setAdapter(adapter);
    }
}
