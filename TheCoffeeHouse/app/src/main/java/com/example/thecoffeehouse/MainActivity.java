package com.example.thecoffeehouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thecoffeehouse.Adapter.CategoryAdapter;
import com.example.thecoffeehouse.model.Category;
import com.example.thecoffeehouse.model.Poster;
import com.example.thecoffeehouse.model.Product;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    public static String DATABASE_NAME = "CoffeeHouse.db";
    public static SQLiteDatabase database;
    RecyclerView recyclerView_cagory_355;
    public static ArrayList<Product> gioHangList = new ArrayList<>();

    CategoryAdapter categoryAdapter;
    NestedScrollView scrollView;
    Button button_dangnhap_355;
    ImageView imageView_dathang_355;
    RelativeLayout chuadn_355;
    RelativeLayout dadn_355;
    public  static String sdt="";
    TextView ten;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = Database.initDatabase(this,DATABASE_NAME);
        recyclerView_cagory_355 = findViewById(R.id.rcv_category_355);
        categoryAdapter = new CategoryAdapter(this);
        scrollView=findViewById(R.id.scrollView_355);
        chuadn_355 = findViewById(R.id.chuadangnhap_355);

        dadn_355 = findViewById(R.id.signed_355);
        ten =findViewById(R.id.ten_main_355);
        int dem=0;
        Cursor cursor = database.rawQuery("Select * from TaiKhoan",null);
        while (cursor.moveToNext())
        {
            if(cursor.getString(1).equals(sdt))
            {
                dem=dem+1;
            }
        }
        if(dem<=0)
        {
            chuadn_355.setVisibility(View.VISIBLE);
            dadn_355.setVisibility(View.GONE);
        }
        else {

            Cursor infor = database.rawQuery("Select * from TaiKhoan where SoDienThoai='"+sdt+"'",null);
            infor.moveToFirst();

            ten.setText(infor.getString(2)+" "+infor.getString(3));

            dadn_355.setVisibility(View.VISIBLE);
            chuadn_355.setVisibility(View.GONE);

        }
        dadn_355.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });


        button_dangnhap_355= findViewById(R.id.dangnhap_button);
        button_dangnhap_355.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DangNhapActivity.class);
                startActivity(intent);
            }
        });

        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.scrollTo(0,0);
            }
        });
        imageView_dathang_355 = findViewById(R.id.dathang);
        imageView_dathang_355.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this, DatHangActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.silde_out_to_left);
            }
        });
    // bottomnav
        bottomNavigationView = findViewById(R.id.bottom_menu);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()){
                    case R.id.news:

                        break;
                    case R.id.dathang:
                        intent =new Intent(MainActivity.this, DatHangActivity.class);
                        if(MainActivity.gioHangList.size()!=0)
                        {
                            DatHangActivity.showGioHang();
                            startActivity(intent);
                        }
                        else {
                            startActivity(intent);
                        }
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.silde_out_to_left);
                        break;
                    case R.id.taikhoan:
                        intent =new Intent(MainActivity.this, ProfileActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.silde_out_to_left);
                        break;
                }
                return false;
            }
        });

        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView_cagory_355.setLayoutManager(linearLayoutManager);
        categoryAdapter.setData(getList());
        recyclerView_cagory_355.setAdapter(categoryAdapter);
    }

    private List<Category> getList() {
        List<Category> list_355 =new ArrayList<>();
        List<Poster> posterList1_355,posterList2_355,posterList3_355;
        Cursor cursor = database.rawQuery("Select * from Poster where Category ='Ưu đãi'; ",null);
        posterList1_355 = new ArrayList<>();
        while (cursor.moveToNext())
        {
            byte[] hinhAnh = cursor.getBlob(3);
            Poster poster =new Poster(cursor.getString(1),cursor.getString(2),hinhAnh,"Order ngay");
            posterList1_355.add(poster);
        }
        Category category_355 = new Category("Ưu đãi đặc biệt",posterList1_355);
        list_355.add(category_355);
        cursor = database.rawQuery("Select * from Poster where Category ='Nhà'; ",null);
        posterList2_355 = new ArrayList<>();
        while (cursor.moveToNext())
        {
            byte[] hinhAnh = cursor.getBlob(3);
            Poster poster =new Poster(cursor.getString(1),cursor.getString(2),hinhAnh,"Chi tiết");
            posterList2_355.add(poster);
        }
        Category category2_355 = new Category("Cập nhật từ Nhà",posterList2_355);
        list_355.add(category2_355);
        cursor = database.rawQuery("Select * from Poster where Category ='Coffee'; ",null);
        posterList3_355 = new ArrayList<>();
        while (cursor.moveToNext())
        {
            byte[] hinhAnh = cursor.getBlob(3);
            Poster poster =new Poster(cursor.getString(1),cursor.getString(2),hinhAnh,"Chi tiết");
            posterList3_355.add(poster);
        }
        Category category3_355 = new Category("#CoffeeLover",posterList3_355);
        list_355.add(category3_355);
        return list_355;
    }
}