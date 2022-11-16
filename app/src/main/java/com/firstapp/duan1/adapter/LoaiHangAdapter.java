package com.firstapp.duan1.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firstapp.duan1.R;
import com.firstapp.duan1.dao.LoaiHangDAO;
import com.firstapp.duan1.model.LoaiHang;

import java.util.ArrayList;
import java.util.HashMap;

public class LoaiHangAdapter extends RecyclerView.Adapter<LoaiHangAdapter.ViewHolder>{
    private ArrayList<LoaiHang> list;
    private Context context;
    private LoaiHangDAO loaiHangDAO;

    public LoaiHangAdapter(ArrayList<LoaiHang> list, Context context, LoaiHangDAO loaiHangDAO) {
        this.list = list;
        this.context = context;
        this.loaiHangDAO = loaiHangDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_ql_loaihang, parent, false);
        return new ViewHolder(view);
    }
    //alo
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMaLoai.setText("Mã Loại:" + list.get(position).getMaloai());
        holder.txtTenLoai.setText("Tên Loại:" + list.get(position).getTenloai());

        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check = loaiHangDAO.xoaLoaiHang(list.get(holder.getAdapterPosition()).getMaloai());
                switch (check){
                    case 1:
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        loadData();
                        break;
                    case 0:
                        Toast.makeText(context, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                        break;
                    case -1:
                        Toast.makeText(context, "Không xóa được vì mã loại còn sản phẩm", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });

        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(list.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMaLoai, txtTenLoai;
        ImageView ivDel, ivEdit;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtMaLoai = itemView.findViewById(R.id.txtMaLoai);
            txtTenLoai = itemView.findViewById(R.id.txtTenLoai);
            ivDel = itemView.findViewById(R.id.ivDel);
            ivEdit = itemView.findViewById(R.id.ivEdit);
        }
    }

    private void showDialog(LoaiHang loaiHang){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_sualoaihang, null);
        builder.setView(view);
        //ánh xạ
        TextView txtMaLoai = view.findViewById(R.id.txtMaLoai);
        EditText edtTenLoai = view.findViewById(R.id.edtTenLoai);

        txtMaLoai.setText("Mã Loại hàng: " + loaiHang.getMaloai());
        edtTenLoai.setText(loaiHang.getTenloai());

        builder.setNegativeButton("Cập nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String tenloai = edtTenLoai.getText().toString();

                boolean check = loaiHangDAO.thayDoiLoaiHang(loaiHang.getMaloai(), tenloai);
                if (check){
                    Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    list = loaiHangDAO.getDSLoaiHang();
                    loadData();
                }
                else {
                    Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void loadData(){
        list.clear();
        list = loaiHangDAO.getDSLoaiHang();
        notifyDataSetChanged();
    }
}


