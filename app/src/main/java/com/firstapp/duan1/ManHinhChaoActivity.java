package com.firstapp.duan1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.Objects;

public class ManHinhChaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chao);

        Objects.requireNonNull(getSupportActionBar()).hide();

        Glide.with(this).load(R.mipmap.loading).into((ImageView) findViewById(R.id.ivlogo));

        new Handler().postDelayed(
                () -> startActivity(new Intent(ManHinhChaoActivity.this, DangNhapActivity.class)), 3_000
        );
    }
}