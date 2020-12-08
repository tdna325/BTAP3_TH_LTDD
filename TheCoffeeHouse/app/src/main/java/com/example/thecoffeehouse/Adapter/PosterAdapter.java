package com.example.thecoffeehouse.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thecoffeehouse.R;
import com.example.thecoffeehouse.model.Poster;

import java.util.List;

public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.PosterViewHolder> {
    private List<Poster> posterList;
    void setData(List<Poster> list){
        this.posterList = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public PosterAdapter.PosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_poster,parent,false);
        return new PosterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder holder, int position) {
        Poster poster = posterList.get(position);

        holder.title_355.setText(poster.getTitle_355());
        holder.noidung_355.setText(poster.getNoidung_355());
        byte[] hinhAnh = poster.getImage_355();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
        holder.image_355.setImageBitmap(bitmap);
        holder.btn_355.setText(poster.getBtn_355());
    }


    @Override
    public int getItemCount() {
        return posterList.size();
    }

    public class PosterViewHolder extends RecyclerView.ViewHolder {
        private TextView title_355;
        private TextView noidung_355;
        private ImageView image_355;
        private Button btn_355;
        public PosterViewHolder(@NonNull View itemView) {
            super(itemView);
            title_355 = itemView.findViewById(R.id.Title_355);
            noidung_355 = itemView.findViewById(R.id.noidung_355);
            image_355 = itemView.findViewById(R.id.image_355);
            btn_355 =  itemView.findViewById(R.id.btn_355);


        }
    }
}
