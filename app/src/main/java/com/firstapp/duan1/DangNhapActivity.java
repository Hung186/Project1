package com.firstapp.duan1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firstapp.duan1.dao.NguoiDungDAO;

public class DangNhapActivity extends AppCompatActivity {
    EditText edtUser, edtPass;
    CheckBox cbRememberMe;
    Button btnLogin;
    TextView tvRegister;
    NguoiDungDAO nguoiDungDAO;
    String loaitaikhoan;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        edtUser = findViewById(R.id.edtUser);
        edtPass = findViewById(R.id.edtPass);
        btnLogin = findViewById(R.id.btnLogin);
        cbRememberMe = findViewById(R.id.cbRememberMe);
        tvRegister = findViewById(R.id.tvRegister);



        nguoiDungDAO = new NguoiDungDAO(DangNhapActivity.this);

        sharedPreferences = getSharedPreferences("THONGTINNGUOIDUNG", MODE_PRIVATE);
        if (cbRememberMe.isChecked()){
            String tennd = sharedPreferences.getString("tennd", "");
            String matkhau = sharedPreferences.getString("matkhau", "");
            edtUser.setText(tennd);
            edtPass.setText(matkhau);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUser.getText().toString();
                String password = edtPass.getText().toString();
                if (nguoiDungDAO.checkDangNhap(username, password)){
                    loaitaikhoan = sharedPreferences.getString("loaitaikhoan", "");
                    if (loaitaikhoan.equals("Admin")){
                        Toast.makeText(DangNhapActivity.this, "Xin chào Admin", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(DangNhapActivity.this, AdminActivity.class));
                    }
                    else if (loaitaikhoan.equals("Customer")){
                        Toast.makeText(DangNhapActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(DangNhapActivity.this, KhachHangActivity.class));
                    }
                    else {
                        Toast.makeText(DangNhapActivity.this, "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(DangNhapActivity.this, "UserName hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}