package com.firstapp.duan1.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firstapp.duan1.R;

import com.firstapp.duan1.dao.SanPhamDAO;
import com.firstapp.duan1.model.SanPham;

import java.util.ArrayList;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ViewHolder>{
    private ArrayList<SanPham> list;
    private Context context;

    public SanPhamAdapter(ArrayList<SanPham> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_ql_sanpham, parent, false);
        return new SanPhamAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTenSP.setText("Tên SP:" + list.get(position).getTensp());
        holder.txtThuongHieu.setText("Thương hiệu:" + list.get(position).getThuonghieu());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTenSP, txtThuongHieu;
        ImageView ivDel, ivEdit;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtTenSP = itemView.findViewById(R.id.txtTenSP);
            txtThuongHieu = itemView.findViewById(R.id.txtThuongHieu);
            ivDel = itemView.findViewById(R.id.ivDel);
            ivEdit = itemView.findViewById(R.id.ivEdit);
        }
    }
}
