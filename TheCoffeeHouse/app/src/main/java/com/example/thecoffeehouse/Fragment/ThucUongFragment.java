package com.example.thecoffeehouse.Fragment;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thecoffeehouse.Adapter.ProductCategoryAdapter;
import com.example.thecoffeehouse.MainActivity;
import com.example.thecoffeehouse.R;
import com.example.thecoffeehouse.model.Product;
import com.example.thecoffeehouse.model.ProductCategory;

import java.util.ArrayList;
import java.util.List;


public class ThucUongFragment extends Fragment {
   public static RecyclerView recyclerView;
   public static LinearLayoutManager linearLayoutManager;
    ProductCategoryAdapter productCategoryAdapter;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thuc_uong, container, false);
        recyclerView = view.findViewById(R.id.rcv_all_product_355);
        linearLayoutManager =new LinearLayoutManager(view.getContext(),RecyclerView.VERTICAL,false);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        productCategoryAdapter = new ProductCategoryAdapter(view.getContext());
        productCategoryAdapter.setData(getList());
        recyclerView.setAdapter(productCategoryAdapter);
         /// recyclerView.post(new Runnable() {
      //      @Override
      //      public void run() {
        //    }
        // });
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        return view ;
    }
    private List<ProductCategory> getList() {
        List<ProductCategory> list = new ArrayList<>();

        list.add(AnhXa(1,1));
        list.add(AnhXa(2,1));
        list.add(AnhXa(3,1));
        list.add(AnhXa(4,1));
        list.add(AnhXa(5,1));
        list.add(AnhXa(6,1));
        return list;
    }
    private ProductCategory AnhXa(int LoaiSP,int Phanloai){
        List<Product> productList;
        Cursor cursor = MainActivity.database.rawQuery("Select * from SanPham where ID_LoaiSP="+LoaiSP+" and PhanLoai = "+Phanloai+";",null);
        productList = new ArrayList<>();
        while (cursor.moveToNext())
        {
            byte[] hinhAnh = cursor.getBlob(5);
            Product product = new Product(cursor.getString(1),cursor.getInt(2),hinhAnh,cursor.getInt(0));
            productList.add(product);
        }
        Cursor title = MainActivity.database.rawQuery("Select * from LoaiSanPham where ID="+LoaiSP,null);
        title.moveToFirst();
        ProductCategory productCategory = new ProductCategory(title.getString(1),productList);
        return productCategory;
    }
}