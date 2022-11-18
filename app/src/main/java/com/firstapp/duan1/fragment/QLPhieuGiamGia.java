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
import com.firstapp.duan1.adapter.PhieuGiamGiaAdapter;
import com.firstapp.duan1.adapter.SanPhamAdapter;
import com.firstapp.duan1.dao.PhieuGiamGiaDAO;
import com.firstapp.duan1.dao.SanPhamDAO;
import com.firstapp.duan1.model.PhieuGiamGia;

import java.util.ArrayList;

public class QLPhieuGiamGia extends Fragment {
    RecyclerView recyclerQLPhieuGiamGia;
    PhieuGiamGiaDAO phieuGiamGiaDAO;
    ArrayList<PhieuGiamGia> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ql_phieugiamgia, container, false);
        recyclerQLPhieuGiamGia = view.findViewById(R.id.recyclerQLPhieuGiamGia);


        //data
        phieuGiamGiaDAO = new PhieuGiamGiaDAO(getContext());

        loadData();


        return view;
    }



    private void loadData() {
        list = phieuGiamGiaDAO.getDSPhieuGiamGia();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerQLPhieuGiamGia.setLayoutManager(linearLayoutManager);
        PhieuGiamGiaAdapter adapter = new PhieuGiamGiaAdapter(list, getContext(), phieuGiamGiaDAO);
        recyclerQLPhieuGiamGia.setAdapter(adapter);
    }

}