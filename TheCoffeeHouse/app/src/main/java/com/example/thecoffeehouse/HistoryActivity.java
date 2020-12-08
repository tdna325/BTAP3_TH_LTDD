package com.example.thecoffeehouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.example.thecoffeehouse.Adapter.HoaDonGroupAdater;
import com.example.thecoffeehouse.model.HoaDonGroup;
import com.example.thecoffeehouse.model.Product;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HistoryActivity extends AppCompatActivity {
    ExpandableListView expandableListView;
    List<HoaDonGroup> list;
    Map<HoaDonGroup,List<Product>> hoaDonGroupListMap;
    HoaDonGroupAdater adater;
    Button dathang;
    LinearLayout chuamh;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        dathang = findViewById(R.id.dathang_ls_355);
        chuamh = findViewById(R.id.chuamuahang_355);
        toolbar = (Toolbar) findViewById(R.id.close_355);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoryActivity.this, DatHangActivity.class);
                startActivity(intent);
            }
        });
        expandableListView = findViewById(R.id.danh_sach_hoadon);
        if (MainActivity.sdt==""){
            expandableListView.setVisibility(View.GONE);
            chuamh.setVisibility(View.VISIBLE);
        }
        else {
            Cursor idTK = MainActivity.database.rawQuery("Select * from TaiKhoan where SoDienThoai='"+MainActivity.sdt+"'",null);
            idTK.moveToFirst();
            Cursor cursor = MainActivity.database.rawQuery("Select * from HoaDon where ID_TK="+idTK.getInt(0),null);
            cursor.moveToFirst();
            if(cursor.getCount()!=0){

                expandableListView.setVisibility(View.VISIBLE);
                chuamh.setVisibility(View.GONE);
                hoaDonGroupListMap = setData();
                list = new ArrayList<>(hoaDonGroupListMap.keySet());
                adater = new HoaDonGroupAdater(list,hoaDonGroupListMap);
                adater.notifyDataSetChanged();
                expandableListView.setAdapter(adater);
            }
            else {
                expandableListView.setVisibility(View.GONE);
                chuamh.setVisibility(View.VISIBLE);
            }
        }

    }
    private Map<HoaDonGroup,List<Product>> setData(){
        Map<HoaDonGroup,List<Product>> listMap = new LinkedHashMap<>();
        Cursor idTK = MainActivity.database.rawQuery("Select * from TaiKhoan where SoDienThoai='"+MainActivity.sdt+"'",null);
        idTK.moveToFirst();
        Cursor cursor = MainActivity.database.rawQuery("Select * from HoaDon where ID_TK="+idTK.getInt(0),null);
        while (cursor.moveToNext()){
            HoaDonGroup hoaDonGroup = new HoaDonGroup(cursor.getInt(0),cursor.getString(3),cursor.getInt(2));
            Cursor chitietHoaDon =MainActivity.database.rawQuery("Select * from ChiTietHoaDon where ID_HoaDon ="+cursor.getInt(0),null);
            List<Product> list = new ArrayList<>();
            while (chitietHoaDon.moveToNext()){
            Product product = new Product(chitietHoaDon.getString(3),chitietHoaDon.getInt(1),chitietHoaDon.getString(5),chitietHoaDon.getInt(2),chitietHoaDon.getInt(4));
            list.add(product);
            }
            listMap.put(hoaDonGroup,list);
        }

        return listMap;
    }
}