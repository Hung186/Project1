package com.firstapp.duan1.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firstapp.duan1.R;
import com.firstapp.duan1.firebase.controller.ControllerLoaiHang;
import com.firstapp.duan1.model.LoaiHang;

import java.util.List;

public class LoaiHangAdapter extends RecyclerView.Adapter<LoaiHangAdapter.ViewHolder> {
    private List<LoaiHang> list;
    private final Context context;
    private final ControllerLoaiHang controllerLoaiHang;

    public LoaiHangAdapter(List<LoaiHang> list, Context context, ControllerLoaiHang controllerLoaiHang) {
        this.list = list;
        this.context = context;
        this.controllerLoaiHang = controllerLoaiHang;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_ql_loaihang, parent, false);
        return new ViewHolder(view);
    }

    //alo
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvProductID.setText("Mã Loại:" + list.get(position).__id);
        holder.tvProductName.setText("Tên Loại:" + list.get(position).displayName);

        holder.ivDelete.setOnClickListener(v -> {
            int check = controllerLoaiHang.removeGetResult(list.get(holder.getAdapterPosition()).__id);
            switch (check) {
                case 1:
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    loadData();
                    break;
                case 2:
                    Toast.makeText(context, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(context, "Không xóa được vì mã loại còn sản phẩm", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        });

        holder.ivEdit.setOnClickListener(v -> showDialog(list.get(holder.getAdapterPosition())));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvProductID, tvProductName;
        ImageView ivDelete, ivEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvProductID = itemView.findViewById(R.id.txtMaLoai);
            tvProductName = itemView.findViewById(R.id.txtTenLoai);
            ivDelete = itemView.findViewById(R.id.ivDel);
            ivEdit = itemView.findViewById(R.id.ivEdit);
        }
    }

    private void showDialog(LoaiHang loaiHang) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = ((Activity) context).getLayoutInflater().inflate(R.layout.dialog_sualoaihang, null);
        builder.setView(view);

        TextView tvProductID = view.findViewById(R.id.txtMaLoai);
        EditText etProductName = view.findViewById(R.id.edtTenLoai);

        tvProductID.setText("Mã Loại hàng: " + loaiHang.__id);
        etProductName.setText(loaiHang.displayName);

        builder.setNegativeButton("Cập nhật", (dialog, which) -> {
            loaiHang.displayName = etProductName.getText().toString();

            boolean check = controllerLoaiHang.set(loaiHang, true);
            if (check) {
                Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                list = controllerLoaiHang.getAll();
                loadData();
            } else {
                Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setPositiveButton("Hủy", (dialog, which) -> {});
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void loadData() {
        list.clear();
        list = controllerLoaiHang.getAll();
        notifyDataSetChanged();
    }
}


