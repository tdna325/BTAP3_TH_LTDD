package com.example.thecoffeehouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    RelativeLayout chuadangnhap;
    RelativeLayout dadangnhap;
    TextView dangxuat;
    TextView name_acc;
    TextView lichsu_355;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        bottomNavigationView = findViewById(R.id.bottom_menu);
        chuadangnhap = findViewById(R.id.chuadangnhap_profile);
        dadangnhap = findViewById(R.id.tab_infor_355);
        dangxuat = findViewById(R.id.dangxuat_355);
        name_acc =findViewById(R.id.name_acc_355);
        lichsu_355 = findViewById(R.id.history_355);
        lichsu_355.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });
        if(MainActivity.sdt=="")
        {
            chuadangnhap.setVisibility(View.VISIBLE);
            dadangnhap.setVisibility(View.GONE);
            dangxuat.setVisibility(View.GONE);
        }
        else {
            Cursor cursor = MainActivity.database.rawQuery("Select * from TaiKhoan where SoDienThoai ='"+MainActivity.sdt+"'",null);
            cursor.moveToFirst();
            name_acc.setText(cursor.getString(2)+" "+cursor.getString(3));
            chuadangnhap.setVisibility(View.GONE);
            dadangnhap.setVisibility(View.VISIBLE);
            dangxuat.setVisibility(View.VISIBLE);
        }
        dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.sdt="";
                chuadangnhap.setVisibility(View.VISIBLE);
                dadangnhap.setVisibility(View.GONE);
                dangxuat.setVisibility(View.GONE);
            }
        });
        chuadangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,DangNhapActivity.class);
                startActivity(intent);
            }
        });
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()){
                    case R.id.news:
                        intent =new Intent(ProfileActivity.this, MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
                        break;
                    case R.id.dathang:
                        intent =new Intent(ProfileActivity.this, DatHangActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
                        break;
                    case R.id.taikhoan:
                        break;
                }
                return false;
            }
        });
    }
}