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
import com.firstapp.duan1.dao.PhieuGiamGiaDAO;
import com.firstapp.duan1.model.LoaiHang;
import com.firstapp.duan1.model.PhieuGiamGia;

import java.util.ArrayList;

public class PhieuGiamGiaAdapter extends RecyclerView.Adapter<PhieuGiamGiaAdapter.ViewHolder>{
    private ArrayList<PhieuGiamGia> list;
    private Context context;
    private PhieuGiamGiaDAO phieuGiamGiaDAO;

    public PhieuGiamGiaAdapter(ArrayList<PhieuGiamGia> list, Context context, PhieuGiamGiaDAO phieuGiamGiaDAO) {
        this.list = list;
        this.context = context;
        this.phieuGiamGiaDAO = phieuGiamGiaDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_ql_phieugiamgia, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtGiaTriGiam.setText("Giá trị giảm: " + list.get(position).getGiatrigiam());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtGiaTriGiam;
        ImageView ivDel;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtGiaTriGiam = itemView.findViewById(R.id.txtGiaTriGiam);
            ivDel = itemView.findViewById(R.id.ivDel);
        }
    }
}
