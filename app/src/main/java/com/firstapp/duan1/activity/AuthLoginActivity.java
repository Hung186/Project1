package com.firstapp.duan1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firstapp.duan1.R;
import com.google.firebase.auth.FirebaseAuth;

public class AuthLoginActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private CheckBox cbRememberMe;
    private Button btnLogin;
    private TextView tvRegister;
    private String loaitaikhoan;

    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        etUsername = findViewById(R.id.edtUser);
        etPassword = findViewById(R.id.edtPass);
        btnLogin = findViewById(R.id.btnLogin);
        cbRememberMe = findViewById(R.id.cbRememberMe);
        tvRegister = findViewById(R.id.tvRegister);

        sharedPreferences = getSharedPreferences("THONGTINNGUOIDUNG", MODE_PRIVATE);

        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            firebaseAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    loaitaikhoan = sharedPreferences.getString("loaitaikhoan", "");

                    if (loaitaikhoan.equals("Admin")){
                        Toast.makeText(AuthLoginActivity.this, "Xin chào Admin", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AuthLoginActivity.this, AdminActivity.class));
                    }
                    else if (loaitaikhoan.equals("Customer")){
                        Toast.makeText(AuthLoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AuthLoginActivity.this, AccountActivity.class));
                    }
                    else {
                        Toast.makeText(AuthLoginActivity.this, "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AuthLoginActivity.this, "UserName hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
            });
        });

    }
}