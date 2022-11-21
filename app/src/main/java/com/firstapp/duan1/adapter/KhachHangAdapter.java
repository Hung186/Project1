package com.firstapp.duan1.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firstapp.duan1.R;
import com.firstapp.duan1.dao.LoaiHangDAO;
import com.firstapp.duan1.model.LoaiHang;
import com.firstapp.duan1.model.NguoiDung;

import java.util.ArrayList;

public class KhachHangAdapter extends RecyclerView.Adapter<KhachHangAdapter.ViewHolder>{
    private ArrayList<NguoiDung> list;
    private Context context;

    public KhachHangAdapter(ArrayList<NguoiDung> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_ql_khachhang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTenKhachHang.setText("Tên Khách Hàng: " + list.get(position).getTennd());
        holder.txtSdt.setText("SĐT: " + list.get(position).getSdt());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTenKhachHang, txtSdt;
        ImageView ivDel;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtTenKhachHang = itemView.findViewById(R.id.txtTenKhachHang);
            txtSdt = itemView.findViewById(R.id.txtSdt);
            ivDel = itemView.findViewById(R.id.ivDel);
        }
    }
}
