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


public class PhoBienFragment extends Fragment {
    public static RecyclerView recyclerView;
    ProductCategoryAdapter productCategoryAdapter;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pho_bien, container, false);
        recyclerView = view.findViewById(R.id.rcv_all_product_355);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(view.getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        productCategoryAdapter = new ProductCategoryAdapter(view.getContext());
        productCategoryAdapter.setData(getList());
        recyclerView.setAdapter(productCategoryAdapter);

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        return view ;
    }
    private List<ProductCategory> getList() {
        List<ProductCategory> list = new ArrayList<>();

        list.add(AnhXa(1));
        return list;
    }
    private ProductCategory AnhXa(int Phobien){
        List<Product> productList;
        Cursor cursor = MainActivity.database.rawQuery("Select * from SanPham where PhoBien="+Phobien+";",null);
        productList = new ArrayList<>();
        while (cursor.moveToNext())
        {
            byte[] hinhAnh = cursor.getBlob(5);
            Product product = new Product(cursor.getString(1),cursor.getInt(2),hinhAnh,cursor.getInt(0));
            productList.add(product);
        }
        ProductCategory productCategory = new ProductCategory("Món được yêu thích",productList);
        return productCategory;
    }
}