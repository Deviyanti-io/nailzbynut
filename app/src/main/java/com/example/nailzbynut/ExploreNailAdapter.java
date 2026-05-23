package com.example.nailzbynut; // Sesuaikan dengan nama package proyekmu

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ExploreNailAdapter extends RecyclerView.Adapter<ExploreNailAdapter.NailViewHolder> {

    private List<NailModel> nailList;

    // Constructor Adapter
    public ExploreNailAdapter(List<NailModel> nailList) {
        this.nailList = nailList;
    }

    // Menghubungkan komponen UI yang ada di dalam item_nail.xml
    public static class NailViewHolder extends RecyclerView.ViewHolder {
        ImageView ivNailImage;
        TextView tvNailName;
        TextView tvNailPrice;

        public NailViewHolder(@NonNull View view) {
            super(view);
            ivNailImage = view.findViewById(R.id.ivNailImage);
            tvNailName = view.findViewById(R.id.tvNailName);
            tvNailPrice = view.findViewById(R.id.tvNailPrice);
        }
    }

    @NonNull
    @Override
    public NailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_nail, parent, false);
        return new NailViewHolder(view);
    }

    // Mengisi komponen UI dengan data kuku berdasarkan urutan posisinya
    @Override
    public void onBindViewHolder(@NonNull NailViewHolder holder, int position) {
        NailModel nail = nailList.get(position);
        holder.tvNailName.setText(nail.getName());
        holder.tvNailPrice.setText(nail.getPrice());
        holder.ivNailImage.setImageResource(nail.getImageResourceId());
    }

    @Override
    public int getItemCount() {
        return nailList.size();
    }
}