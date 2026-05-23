package com.example.nailzbynut;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ServicesActivity extends AppCompatActivity {

    private RecyclerView rvServices;
    private ServiceAdapter adapter;
    private List<ServiceModel> serviceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        rvServices = findViewById(R.id.rvServices);

        // Menyusun daftar layanan secara vertikal ke bawah
        rvServices.setLayoutManager(new LinearLayoutManager(this));

        // MENGISI KONTEN LAYANAN SALON (Sesuai Konsep Premium AI)
        serviceList = new ArrayList<>();

        // PERBAIKAN: Semua angka harga sekarang dibungkus tanda petik dua ("...") agar menjadi String
        serviceList.add(new ServiceModel("Basic Manicure", "Pembersihan kuku, perapian kutikula, pembentukan kuku, dan pijat tangan relaksasi.", "45000", R.drawable.nail1));
        serviceList.add(new ServiceModel("Spa Pedicure", "Rendam kaki dengan garam spa, scrub pengangkat sel kulit mati, masker kaki, dan perawatan kuku.", "65000", R.drawable.nail1));
        serviceList.add(new ServiceModel("Gel Polish Solid Color", "Pewarnaan kuku menggunakan kutek gel premium tahan lama hingga 4 minggu dengan pengeringan lampu UV.", "85000", R.drawable.nail1));
        serviceList.add(new ServiceModel("Premium Nail Art Custom", "Seni lukis kuku manual tingkat lanjut, termasuk pembuatan motif marmer, floral, atau gradasi sesuai request.", "120000", R.drawable.nail1));
        serviceList.add(new ServiceModel("Acrilic Nail Extension", "Penyambungan kuku buatan berbahan akrilik premium untuk memberikan hasil kuku panjang yang kokoh dan anggun.", "150000", R.drawable.nail1));
        serviceList.add(new ServiceModel("Add-On 3D Jewelry / Charm", "Penambahan aksesori kuku timbul seperti permata, mutiara, atau hiasan pita 3D per kuku.", "15000", R.drawable.nail1));

        // Pasang data ke adapter agar langsung tampil di RecyclerView
        adapter = new ServiceAdapter(serviceList);
        rvServices.setAdapter(adapter);
    }
}