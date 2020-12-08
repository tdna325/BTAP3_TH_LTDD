package com.example.thecoffeehouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thecoffeehouse.Adapter.ProductCategoryAdapter;
import com.example.thecoffeehouse.Adapter.ViewPagerAdapter;
import com.example.thecoffeehouse.Fragment.DoAnFragment;
import com.example.thecoffeehouse.Fragment.PhoBienFragment;
import com.example.thecoffeehouse.Fragment.ThucUongFragment;
import com.example.thecoffeehouse.model.Product;
import com.example.thecoffeehouse.model.ProductCategory;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DatHangActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    TabLayout tabLayout;
    ViewPager viewPager;
    Button danhmuc_355;
    public static RelativeLayout gioHang_355;
    public static TextView tongsoluong_355;
    public static TextView tongthanhtien_355;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_hang);
        MainActivity.database = Database.initDatabase(this,MainActivity.DATABASE_NAME);
        gioHang_355 = findViewById(R.id.gioHang_355);
        tongsoluong_355 = findViewById(R.id.tong_so_luong_355);
        tongthanhtien_355 = findViewById(R.id.tong_thanh_tien_355);
        danhmuc_355 =findViewById(R.id.danhmuc_product_355);
        tabLayout = findViewById(R.id.tab_355);
        viewPager = findViewById(R.id.view_355);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        danhmuc_355.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(DatHangActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                dialog.setContentView(R.layout.dialog_filter_product);
                Window window =dialog.getWindow();
                if (window == null)
                {
                    return;
                }
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable( new ColorDrawable(Color.TRANSPARENT));
                WindowManager.LayoutParams layoutParams =window.getAttributes();
                layoutParams.gravity = Gravity.BOTTOM | Gravity.LEFT;
                window.setAttributes(layoutParams);
                ListView listView = dialog.findViewById(R.id.danhmuc_sanpham);
                Cursor cursor= MainActivity.database.rawQuery("Select * from LoaiSanPham",null);
                List<String> danhmuc = new ArrayList<>();
                danhmuc.add("Món được yêu thích");
                while (cursor.moveToNext())
                {

                    danhmuc.add(cursor.getString(1));
                }
                ArrayAdapter arrayAdapter = new ArrayAdapter(dialog.getContext(), android.R.layout.simple_list_item_1,danhmuc);
                listView.setAdapter(arrayAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        RecyclerView.SmoothScroller smoothScroller = new
                                LinearSmoothScroller(DatHangActivity.this) {
                                    @Override
                                    protected int getVerticalSnapPreference() {
                                        return LinearSmoothScroller.SNAP_TO_START;
                                    }
                                };
                        switch (position)
                        {
                            case 0:
                                tabLayout.getTabAt(0).select();
                                dialog.dismiss();
                                break;
                            case 1:
                                tabLayout.getTabAt(1).select();
                                smoothScroller.setTargetPosition(0);
                                ThucUongFragment.linearLayoutManager.startSmoothScroll(smoothScroller);
                                dialog.dismiss();
                                break;
                            case 2:
                                tabLayout.getTabAt(1).select();
                                smoothScroller.setTargetPosition(1);
                                ThucUongFragment.linearLayoutManager.startSmoothScroll(smoothScroller);
                                dialog.dismiss();
                                break;
                            case 3:


                                tabLayout.getTabAt(1).select();
                                smoothScroller.setTargetPosition(2);
                                ThucUongFragment.linearLayoutManager.startSmoothScroll(smoothScroller);
                                dialog.dismiss();
                                break;
                            case 4:
                                tabLayout.getTabAt(1).select();
                                smoothScroller.setTargetPosition(3);
                                ThucUongFragment.linearLayoutManager.startSmoothScroll(smoothScroller);
                                dialog.dismiss();
                                break;
                            case 5:
                                tabLayout.getTabAt(1).select();
                                smoothScroller.setTargetPosition(4);
                                ThucUongFragment.linearLayoutManager.startSmoothScroll(smoothScroller);
                                dialog.dismiss();
                                break;
                            case 6:
                                tabLayout.getTabAt(1).select();
                                smoothScroller.setTargetPosition(5);
                                ThucUongFragment.linearLayoutManager.startSmoothScroll(smoothScroller);
                                dialog.dismiss();
                                break;
                            case 7:
                                tabLayout.getTabAt(2).select();
                                DoAnFragment.recyclerView.scrollToPosition(0);
                                dialog.dismiss();
                                break;



                        }
                    }
                });
                dialog.show();
            }
        });
        showGioHang();
        gioHang_355.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatHangActivity.this,GioHangActivity.class);
                startActivity(intent);
            }
        });
        bottomNavigationView = findViewById(R.id.bottom_menu);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()){
                    case R.id.news:
                        intent =new Intent(DatHangActivity.this, MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
                        break;
                    case R.id.dathang:
                        break;
                    case R.id.taikhoan:
                        intent =new Intent(DatHangActivity.this, ProfileActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.silde_out_to_left);
                        break;
                }
                return false;
            }
        });


    }
    public static  void showGioHang(){
        if(MainActivity.gioHangList.size()>0)
        {

            int tongso_355=0;
            int tongTien_355=0;
            for (int i = 0;i<MainActivity.gioHangList.size();i++)
            {
                tongso_355 = tongso_355+MainActivity.gioHangList.get(i).getSoluong();
                tongTien_355 = tongTien_355 + MainActivity.gioHangList.get(i).getThanhTien();
            }
            tongsoluong_355.setText(tongso_355+"");
            Locale localeVN = new Locale("vi", "VN");
            NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
            String str1 = currencyVN.format(tongTien_355*1000);
            tongthanhtien_355.setText(str1);
            gioHang_355.setVisibility(View.VISIBLE);

        }
        else {
            gioHang_355.setVisibility(View.GONE);
        }
    }


}