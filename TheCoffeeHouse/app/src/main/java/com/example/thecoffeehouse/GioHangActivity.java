package com.example.thecoffeehouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thecoffeehouse.Adapter.GioHangAdapter;
import com.example.thecoffeehouse.model.Product;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class GioHangActivity extends AppCompatActivity {
    static RecyclerView recyclerView;
    static TextView thanhTien;
    static TextView value_TongTien;
    static TextView value_TongTien_2;
    EditText hovaten_355;
    EditText sdt_355;
    TextView diachi_355;
    TextView ktraDN_355;
    TextView back_btn;
    RelativeLayout dathang_355;
    static int tongTien=0;

    public static GioHangAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        thanhTien = findViewById(R.id.thanhtien_giohang_355);
        value_TongTien = findViewById(R.id.value_tongtien);
        back_btn = findViewById(R.id.icon_back);
        dathang_355 =findViewById(R.id.btn_dathang_355);
        value_TongTien_2 = findViewById(R.id.tongtien_02_355);
        recyclerView =findViewById(R.id.rcv_chitietdonhang_355);
        ktraDN_355 = findViewById(R.id.ktra_dangnhap_355);
        hovaten_355 = findViewById(R.id.hovaten_355);
        sdt_355 = findViewById(R.id.sdt_355);
        diachi_355 = findViewById(R.id.address_355);
        if(MainActivity.sdt ==""){
            ktraDN_355.setText("Đăng nhập để mua hàng");
            value_TongTien_2.setVisibility(View.GONE);
            hovaten_355.setText("");
            sdt_355.setText("");
            diachi_355.setText("Bạn chưa chọn địa chỉ");
        }
        else {
            Cursor infor = MainActivity.database.rawQuery("Select * from TaiKhoan Where SoDienThoai = '"+MainActivity.sdt+"'",null);
            infor.moveToFirst();
            hovaten_355.setText("   "+infor.getString(2)+" "+infor.getString(3));
            sdt_355.setText("   "+infor.getString(1));
            diachi_355.setText(infor.getString(5));
            ktraDN_355.setText("Đặt hàng");
            value_TongTien_2.setVisibility(View.VISIBLE);
        }
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatHangActivity.showGioHang();
                finish();

            }
        });
        MainActivity.database = Database.initDatabase(this,MainActivity.DATABASE_NAME);
        setTongTien(this);
        dathang_355.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.sdt ==""){
                    Intent   intent = new Intent(GioHangActivity.this,DangNhapActivity.class);
                    startActivity(intent);
                }
                else {
                    ContentValues contentValues;
                    contentValues =new ContentValues();
                    Cursor idTK = MainActivity.database.rawQuery("Select * from TaiKhoan where SoDienThoai='"+MainActivity.sdt+"'",null);
                    idTK.moveToFirst();
                    contentValues.put("ID_TK",idTK.getInt(0));
                    contentValues.put("TongTien",tongTien);
                    Date c = Calendar.getInstance().getTime();
                    System.out.println("Current time => " + c);

                    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    String formattedDate = df.format(c);
                    contentValues.put("NgayMua",formattedDate);
                    MainActivity.database.insert("HoaDon",null,contentValues);
                    Cursor cursor = MainActivity.database.rawQuery("Select * from HoaDon where ID_TK="+idTK.getInt(0)+" and NgayMua='"+formattedDate+"'",null);
                    cursor.moveToFirst();
                    for(int i =0;i<MainActivity.gioHangList.size();i++)
                    {

                        contentValues =new ContentValues();
                        contentValues.put("ID_HoaDon",cursor.getInt(0));
                        contentValues.put("ID_SanPham",MainActivity.gioHangList.get(i).getId_product_355());
                        contentValues.put("ThanhTien",MainActivity.gioHangList.get(i).getThanhTien());
                        contentValues.put("TenSP",MainActivity.gioHangList.get(i).getName_product_355());
                        contentValues.put("SoLuong",MainActivity.gioHangList.get(i).getSoluong());
                        contentValues.put("Size",MainActivity.gioHangList.get(i).getSize());
                        MainActivity.database.insert("ChiTietHoaDon",null,contentValues);

                    }
                    DatHangActivity.showGioHang();
                    MainActivity.gioHangList.clear();
                    Toast.makeText(GioHangActivity.this,"Đặt hàng thành công! Mã đơn hàng : "+cursor.getInt(0),Toast.LENGTH_SHORT).show();
                    DatHangActivity.showGioHang();
                    finish();
                }
            }
        });
    }
    public static void setTongTien(Context context){
        tongTien=0;
        adapter = new GioHangAdapter();
        adapter.setData(context,MainActivity.gioHangList);
        LinearLayoutManager linearLayoutManager = new  LinearLayoutManager(context,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        for(int i =0;i<MainActivity.gioHangList.size();i++)
        {
            tongTien = tongTien + MainActivity.gioHangList.get(i).getThanhTien();
        }
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String str1 = currencyVN.format(tongTien*1000);
        thanhTien.setText(str1);
        value_TongTien.setText(str1);
        value_TongTien_2.setText(str1);
    }

}