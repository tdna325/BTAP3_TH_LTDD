package com.example.thecoffeehouse.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thecoffeehouse.DatHangActivity;
import com.example.thecoffeehouse.GioHangActivity;
import com.example.thecoffeehouse.MainActivity;
import com.example.thecoffeehouse.R;
import com.example.thecoffeehouse.model.Product;

import java.util.ArrayList;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.GioHangViewHolder> {
    private Context context;

    private ArrayList<Product> arrayList;
    public void setData(Context context,ArrayList<Product> list)
    {
        this.context =context;
        this.arrayList =   list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GioHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(context).inflate(R.layout.row_giohang,parent,false);

        return new GioHangViewHolder(view);
    }
    public  int thanhtien;
    @Override
    public void onBindViewHolder(@NonNull GioHangViewHolder holder, int position) {
        Product product= arrayList.get(position);
        holder.name_sp_355.setText(product.getName_product_355());
        holder.size_sp_355.setText(product.getSize());
        holder.gia_sp_355.setText(product.getThanhTien() +".000 đ");
        holder.soluong_355.setText(product.getSoluong()+"");
        holder.name_sp_355.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_chitietsanpham);
                Window window =dialog.getWindow();
                if (window == null)
                {
                    return;
                }
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable( new ColorDrawable(Color.TRANSPARENT));
                WindowManager.LayoutParams layoutParams =window.getAttributes();
                layoutParams.gravity = Gravity.CENTER;
                window.setAttributes(layoutParams);
                ImageView imageView_355 = dialog.findViewById(R.id.image_ctsp_355);
                TextView name_ctsp_355 = dialog.findViewById(R.id.name_ctsp_355);
                TextView gia_ctsp_355 = dialog.findViewById(R.id.gia_ctsp_355);
                RadioButton lon_355 = dialog.findViewById(R.id.size_lon_355);
                Button minus_sp_355 = dialog.findViewById(R.id.btn_minus_ctsp_355);
                Button add_sp_355 =dialog.findViewById(R.id.btn_add_ctsp_355);
                TextView soluong_355 = dialog.findViewById(R.id.soluong_355);
                TextView thanhtien_355 = dialog.findViewById(R.id.thanhtien_355);
                if(product.getSize()=="Lớn")
                {
                    lon_355.setChecked(true);
                }
                byte[] hinhAnh = product.getImage_product_355();
                Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
                imageView_355.setImageBitmap(bitmap);
                name_ctsp_355.setText(product.getName_product_355());
                gia_ctsp_355.setText(product.getPrice_prodcuct_355()+".000 đ");
                soluong_355.setText(holder.soluong_355.getText().toString());
                thanhtien =product.getThanhTien();
                thanhtien_355.setText(thanhtien+".000 đ");
                RadioGroup size_355 = dialog.findViewById(R.id.size_355);
                size_355.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if(checkedId == R.id.size_lon_355)
                        {

                            thanhtien = thanhtien +Integer.parseInt(soluong_355.getText().toString())*6;
                            thanhtien_355.setText(thanhtien+".000 đ");
                        }
                        else {
                            thanhtien =thanhtien-Integer.parseInt(soluong_355.getText().toString())*6;
                            thanhtien_355.setText(thanhtien+".000 đ");
                        }
                    }
                });
                minus_sp_355.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int soluong =Integer.parseInt(soluong_355.getText().toString());
                        soluong = soluong - 1;
                        soluong_355.setText(soluong+"");
                        if (Integer.parseInt(soluong_355.getText().toString())<1)
                        {
                            soluong_355.setText("0");

                            minus_sp_355.setBackgroundResource(R.drawable.bg_button_minus);

                        }
                        thanhtien =(Integer.parseInt(soluong_355.getText().toString())*product.getPrice_prodcuct_355());
                        if(lon_355.isChecked())
                        {
                            thanhtien=thanhtien-Integer.parseInt(soluong_355.getText().toString())*6;
                        }

                        thanhtien_355.setText(thanhtien+".000 đ");
                        if (Integer.parseInt(soluong_355.getText().toString())==0)
                        {
                            thanhtien_355.setText("Bỏ chọn món");
                        }


                    }
                });
                add_sp_355.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int soluong =Integer.parseInt(soluong_355.getText().toString());
                        soluong = soluong + 1;
                        soluong_355.setText(soluong+"");
                        if (Integer.parseInt(soluong_355.getText().toString())>10)
                        {
                            soluong_355.setText("10");
                        }
                        if (Integer.parseInt(soluong_355.getText().toString())>1)
                        {
                            minus_sp_355.setBackgroundResource(R.drawable.bg_button_minus_red);

                        }
                        thanhtien =(Integer.parseInt(soluong_355.getText().toString())*product.getPrice_prodcuct_355());
                        if(lon_355.isChecked())
                        {
                            thanhtien=thanhtien+Integer.parseInt(soluong_355.getText().toString())*6;
                        }

                        thanhtien_355.setText(thanhtien+".000 đ");

                    }
                });
                thanhtien_355.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Integer.parseInt(soluong_355.getText().toString())==0)
                        {
                            for (int i =0;i<MainActivity.gioHangList.size();i++)
                            {
                                if(MainActivity.gioHangList.get(i).getId_product_355()==product.getId_product_355())
                                {
                                    MainActivity.gioHangList.remove(i);
                                }
                            }
                            dialog.cancel();
                            if(MainActivity.gioHangList.size()==0)
                            {
                                Intent intent = new Intent(context,DatHangActivity.class);
                                context.startActivity(intent);
                            }
                        }
                        else
                        {
                            String size;
                            if(lon_355.isChecked())
                            {
                                size ="Lớn";
                            }
                            else
                            {
                                size = "Vừa";
                            }
                                for (int i =0;i<MainActivity.gioHangList.size();i++)
                                {
                                    if(MainActivity.gioHangList.get(i).getId_product_355()==product.getId_product_355())
                                    {
                                        MainActivity.gioHangList.get(i).setThanhTien(thanhtien);
                                        MainActivity.gioHangList.get(i).setSoluong(Integer.parseInt(soluong_355.getText().toString()));
                                        MainActivity.gioHangList.get(i).setSize(size);
                                    }
                                }
                            notifyDataSetChanged();
                            dialog.cancel();
                        }
                        GioHangActivity.setTongTien(context);

                    }

                });

                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public  class GioHangViewHolder extends RecyclerView.ViewHolder {
            private TextView name_sp_355;
        TextView size_sp_355;
        TextView soluong_355;
        TextView gia_sp_355;
       public GioHangViewHolder(@NonNull View itemView) {
           super(itemView);
           name_sp_355 = itemView.findViewById(R.id.name_sp_giohang_355);
           size_sp_355 = itemView.findViewById(R.id.size_giohang_355);
           soluong_355  =itemView.findViewById(R.id.soluong_giohang);
           gia_sp_355 = itemView.findViewById(R.id.tongtien_giohang_355);
       }
   }

}
