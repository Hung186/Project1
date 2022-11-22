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
import com.firstapp.duan1.firebase.controller.ControllerPhieuGiamGia;
import com.firstapp.duan1.model.PhieuGiamGia;

import java.util.List;

public class PhieuGiamGiaAdapter extends RecyclerView.Adapter<PhieuGiamGiaAdapter.ViewHolder>{
    private final List<PhieuGiamGia> list;
    private final Context context;
    private final ControllerPhieuGiamGia controllerPhieuGiamGia;

    public PhieuGiamGiaAdapter(List<PhieuGiamGia> list, Context context, ControllerPhieuGiamGia controllerPhieuGiamGia) {
        this.list = list;
        this.context = context;
        this.controllerPhieuGiamGia = controllerPhieuGiamGia;
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
        holder.tvReductionAmount.setText("Giá trị giảm: " + list.get(position).percentage);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvReductionAmount;
        ImageView ivDelete;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            tvReductionAmount = itemView.findViewById(R.id.txtGiaTriGiam);
            ivDelete = itemView.findViewById(R.id.ivDel);
        }
    }
}
