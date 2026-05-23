package com.example.nailzbynut;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {

    private List<ServiceModel> serviceList;

    public ServiceAdapter(List<ServiceModel> serviceList) {
        this.serviceList = serviceList;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service, parent, false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        ServiceModel service = serviceList.get(position);

        // REVISI TOTAL: Menggunakan nama getter yang sinkron dengan ServiceModel.java
        holder.tvServiceName.setText(service.getServiceName());
        holder.tvServiceDesc.setText(service.getServiceDesc());
        holder.tvServicePrice.setText("Mulai dari Rp " + service.getPrice());
        holder.ivServiceImage.setImageResource(service.getImageResId());

        // Aksi interaktif ketika salah satu item layanan diklik
        holder.itemView.setOnClickListener(v -> {
            // Jika kamu memiliki logika navigasi ke halaman detail/booking, kodenya ditaruh di sini
        });
    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }

    public static class ServiceViewHolder extends RecyclerView.ViewHolder {
        ImageView ivServiceImage;
        TextView tvServiceName, tvServiceDesc, tvServicePrice;

        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            ivServiceImage = itemView.findViewById(R.id.ivServiceImage);
            tvServiceName = itemView.findViewById(R.id.tvServiceName);
            tvServiceDesc = itemView.findViewById(R.id.tvServiceDesc);
            tvServicePrice = itemView.findViewById(R.id.tvServicePrice);
        }
    }
}