package com.example.nailzbynut;

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
        ImageView btnHeart; // Menambahkan deklarasi untuk tombol heart agar tidak merah
        TextView tvNailName;
        TextView tvNailPrice;

        public NailViewHolder(@NonNull View view) {
            super(view);
            ivNailImage = view.findViewById(R.id.ivNailImage);
            tvNailName = view.findViewById(R.id.tvNailName);
            tvNailPrice = view.findViewById(R.id.tvNailPrice);
            btnHeart = view.findViewById(R.id.heart_n1); // Sesuaikan ID ini dengan ID icon heart di item_nail.xml Anda
        }
    }

    @NonNull
    @Override
    public NailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_nail, parent, false);
        return new NailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NailViewHolder holder, int position) {
        // PERBAIKAN: Mengubah wishlistList menjadi nailList sesuai variabel yang dideklarasikan di atas
        NailModel model = nailList.get(position);

        // PERBAIKAN: Menyesuaikan pemanggilan nama komponen UI dengan yang ada di NailViewHolder
        holder.tvNailName.setText(model.getName());
        holder.tvNailPrice.setText(model.getPrice());
        holder.ivNailImage.setImageResource(model.getImageResId());

        // Logika status favorit
        if (model.isFavorite()) {
            holder.btnHeart.setImageResource(R.drawable.ic_heart_on);
        } else {
            holder.btnHeart.setImageResource(R.drawable.ic_heart_off);
        }

        // Aksi ketika tombol heart diklik
        holder.btnHeart.setOnClickListener(v -> {
            if (model.isFavorite()) {
                model.setFavorite(false);
                holder.btnHeart.setImageResource(R.drawable.ic_heart_off);
            } else {
                model.setFavorite(true);
                holder.btnHeart.setImageResource(R.drawable.ic_heart_on);
            }
        });
    }

    @Override
    public int getItemCount() {
        return nailList.size();
    }
}