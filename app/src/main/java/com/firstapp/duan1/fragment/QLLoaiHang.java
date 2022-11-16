package com.firstapp.duan1.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

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
import java.util.HashMap;

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
        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        return view;
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_them_loaihang, null);
        builder.setView(view);
        //ánh xạ
        EditText edtTenLoai = view.findViewById(R.id.edtTenLoai);

        builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String tenloai = edtTenLoai.getText().toString();
                if (tenloai.equals("")){
                    Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                }
                else {

                    boolean check = loaiHangDAO.themLoaiHang(tenloai);
                    if (check) {
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        list = loaiHangDAO.getDSLoaiHang();
                        loadData();
                    } else {
                        Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    private void loadData() {
        list = loaiHangDAO.getDSLoaiHang();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerLoaiHang.setLayoutManager(linearLayoutManager);
        LoaiHangAdapter adapter = new LoaiHangAdapter(list, getContext(), loaiHangDAO);
        recyclerLoaiHang.setAdapter(adapter);
    }
}
