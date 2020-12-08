package com.example.thecoffeehouse.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thecoffeehouse.DatHangActivity;
import com.example.thecoffeehouse.GioHangActivity;
import com.example.thecoffeehouse.MainActivity;
import com.example.thecoffeehouse.R;
import com.example.thecoffeehouse.model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> list;
    public int thanhtien;
    public int gia;
    public void setData(List<Product> list){
        this.list = list;
        notifyDataSetChanged();
    }
    private Context context;
    public ProductAdapter(Context context){
        this.context =context;
    }
    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product,parent,false);

        return new ProductViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = list.get(position);
        holder.mName_355.setText(product.getName_product_355());
        holder.mGia_355.setText(product.getPrice_prodcuct_355()+".000đ");
        byte[] hinhAnh = product.getImage_product_355();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
        holder.mhinhAnh_355.setImageBitmap(bitmap);
        holder.cardView_355.setOnClickListener(new View.OnClickListener() {
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
                imageView_355.setImageBitmap(bitmap);
                name_ctsp_355.setText(product.getName_product_355());
                gia_ctsp_355.setText(product.getPrice_prodcuct_355()+".000 đ");
                gia=product.getPrice_prodcuct_355();
                soluong_355.setText("1");
                thanhtien =(Integer.parseInt(soluong_355.getText().toString())*product.getPrice_prodcuct_355());
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
                soluong_355.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {

                    }
                });
                minus_sp_355.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int soluong =Integer.parseInt(soluong_355.getText().toString());
                        soluong = soluong - 1;
                        soluong_355.setText(soluong+"");
                        if (Integer.parseInt(soluong_355.getText().toString())<2)
                        {
                            soluong_355.setText("1");
                            minus_sp_355.setBackgroundResource(R.drawable.bg_button_minus);
                        }
                        thanhtien =(Integer.parseInt(soluong_355.getText().toString())*product.getPrice_prodcuct_355());
                        if(lon_355.isChecked())
                        {
                            thanhtien=thanhtien+Integer.parseInt(soluong_355.getText().toString())*6;
                        }
                        thanhtien_355.setText(thanhtien+".000 đ");


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
                        String size;
                        if(lon_355.isChecked())
                        {
                            size ="Lớn";
                        }
                        else
                        {
                            size = "Vừa";
                        }
                        int dem =0;
                        if(MainActivity.gioHangList.size()>0)
                        {
                            for (int i =0;i<MainActivity.gioHangList.size();i++)
                            {

                                if(MainActivity.gioHangList.get(i).getId_product_355()==product.getId_product_355())
                                {
                                    dem=dem+1;
                                }
                            }
                            if(dem != 0){
                                for (int i =0;i<MainActivity.gioHangList.size();i++)
                                {

                                    if(MainActivity.gioHangList.get(i).getId_product_355()==product.getId_product_355())
                                    {
                                        MainActivity.gioHangList.get(i).setThanhTien(thanhtien);
                                        MainActivity.gioHangList.get(i).setSoluong(Integer.parseInt(soluong_355.getText().toString()));
                                        MainActivity.gioHangList.get(i).setSize(size);
                                        Toast.makeText(context,"Đã thêm sản phẩm "+MainActivity.gioHangList.get(i).getName_product_355() + " vào giỏ hàng",Toast.LENGTH_SHORT).show();
                                        DatHangActivity.showGioHang();
                                    }
                                }
                            }
                            else {
                                Cursor cursor = MainActivity.database.rawQuery("Select * from SanPham where ID="+product.getId_product_355(),null);
                                cursor.moveToFirst();
                                byte[] hinhAnh = cursor.getBlob(5);
                                Product product = new Product(cursor.getString(1),cursor.getInt(0),size,thanhtien,Integer.parseInt(soluong_355.getText().toString()),hinhAnh,gia);
                                MainActivity.gioHangList.add(product);
                                DatHangActivity.showGioHang();
                                Toast.makeText(context,"Đã thêm sản phẩm "+cursor.getString(1) + " vào giỏ hàng",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {

                            Cursor cursor = MainActivity.database.rawQuery("Select * from SanPham where ID="+product.getId_product_355(),null);
                            cursor.moveToFirst();
                            byte[] hinhAnh = cursor.getBlob(5);
                            Product product = new Product(cursor.getString(1),cursor.getInt(0),size,thanhtien,Integer.parseInt(soluong_355.getText().toString()),hinhAnh,gia);
                            MainActivity.gioHangList.add(product);
                            DatHangActivity.showGioHang();
                            Toast.makeText(context,"Đã thêm sản phẩm "+cursor.getString(1) + " vào giỏ hàng",Toast.LENGTH_SHORT).show();
                        }

                        dialog.cancel();
                    }

                });

                dialog.show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        private TextView mName_355;
        private TextView mGia_355;
        private ImageView mhinhAnh_355;
        private Button btn_add_355;
        private CardView cardView_355;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            mName_355 = itemView.findViewById(R.id.name_product_355);
            mGia_355 =itemView.findViewById(R.id.price_product_355);
            mhinhAnh_355 = itemView.findViewById(R.id.image_product_355);
            btn_add_355 = itemView.findViewById(R.id.btn_add_355);
            cardView_355 = itemView.findViewById(R.id.muasanpham_355);

        }
    }

}
