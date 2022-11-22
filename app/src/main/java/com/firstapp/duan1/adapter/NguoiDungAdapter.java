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
import com.firstapp.duan1.model.NguoiDung;

import java.util.List;

public class NguoiDungAdapter extends RecyclerView.Adapter<NguoiDungAdapter.ViewHolder>{
    private final List<NguoiDung> list;
    private final Context context;

    public NguoiDungAdapter(List<NguoiDung> list, Context context) {
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
        holder.tvCustomerName.setText("Tên Khách Hàng: " + list.get(position).fullName);
        holder.tvCustomerPhoneNumber.setText("SĐT: " + list.get(position).phoneNumber);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvCustomerName, tvCustomerPhoneNumber;
        ImageView ivDel;
        public ViewHolder(@NonNull View itemView){
            super(itemView);

            tvCustomerName = itemView.findViewById(R.id.txtTenKhachHang);
            tvCustomerPhoneNumber = itemView.findViewById(R.id.txtSdt);
            ivDel = itemView.findViewById(R.id.ivDel);
        }
    }
}
