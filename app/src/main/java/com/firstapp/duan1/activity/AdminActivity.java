package com.firstapp.duan1.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.firstapp.duan1.R;
import com.firstapp.duan1.fragment.QLKhachHang;
import com.firstapp.duan1.fragment.QLLoaiHang;
import com.firstapp.duan1.fragment.QLPhieuGiamGia;
import com.firstapp.duan1.fragment.QLSanPham;
import com.google.android.material.navigation.NavigationView;

public class AdminActivity extends AppCompatActivity {
    DrawerLayout drawerLayoutAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Toolbar toolbarAdmin = findViewById(R.id.toolBarAdmin);
        FrameLayout frameLayoutAdmin = findViewById(R.id.frameLayoutAdmin);
        NavigationView navigationViewAdmin = findViewById(R.id.navigationViewAdmin);
        drawerLayoutAdmin = findViewById(R.id.drawerLayoutAdmin);
        View headerlayout = navigationViewAdmin.getHeaderView(0);
        TextView txtTen = headerlayout.findViewById(R.id.txtTen);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);

        navigationViewAdmin.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.mQLLoaiHang:
                        fragment = new QLLoaiHang();
                        break;
                    case R.id.mQLSanPham:
                        fragment = new QLSanPham();
                        break;
                    case R.id.mQLKhachHang:
                        fragment = new QLKhachHang();
                        break;
                    case R.id.mQLPhieuGiamGia:
                        fragment = new QLPhieuGiamGia();
                        break;
                    case R.id.mThoat:
                        Intent intent = new Intent(AdminActivity.this, AuthLoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        break;
                    case R.id.mQLThongKe:
                        // TODO
                        break;
                    default:
                        // TODO
                        break;
                }
                if (fragment != null){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.frameLayoutAdmin, fragment)
                            .commit();
                    toolbarAdmin.setTitle(item.getTitle());
                }


                drawerLayoutAdmin.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            drawerLayoutAdmin.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}