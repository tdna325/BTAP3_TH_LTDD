package com.example.thecoffeehouse.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thecoffeehouse.R;
import com.example.thecoffeehouse.model.ProductCategory;

import java.util.List;

public class ProductCategoryAdapter extends RecyclerView.Adapter<ProductCategoryAdapter.ProductCategoryViewHolder>{
    private Context context;
    private List<ProductCategory> productCategoryList;

    public ProductCategoryAdapter(Context context) {
        this.context =context;
    }
    public  void  setData(List<ProductCategory> list)
    {
        this.productCategoryList = list;
    }
    @NonNull
    @Override
    public ProductCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product_category,parent,false);
        return new ProductCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductCategoryViewHolder holder, int position) {
        ProductCategory productCategory = productCategoryList.get(position);
        holder.title_product_355.setText(productCategory.getTitle_355());
        GridLayoutManager gridLayoutManager= new GridLayoutManager(context,2);
        holder.recyclerView_product_355.setLayoutManager(gridLayoutManager);
        holder.recyclerView_product_355.setFocusable(false);
        holder.recyclerView_product_355.setNestedScrollingEnabled(false);

        ProductAdapter productAdapter =new ProductAdapter(context);
        productAdapter.setData(productCategory.getList_355());
        holder.recyclerView_product_355.setAdapter(productAdapter);
    }

    @Override
    public int getItemCount() {
        return productCategoryList.size();
    }

    public class ProductCategoryViewHolder extends RecyclerView.ViewHolder {
        private TextView title_product_355;
        private RecyclerView recyclerView_product_355;
        public ProductCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            title_product_355 = itemView.findViewById(R.id.title_product_355);
            recyclerView_product_355 = itemView.findViewById(R.id.rcv_product_355);
        }
    }
}
