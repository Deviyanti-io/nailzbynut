package com.example.nailzbynut; // Sesuaikan dengan nama package proyekmu

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CustomNailReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_nail_review);

        ImageView btnBack = findViewById(R.id.btnBack);
        TextView tvShape = findViewById(R.id.tvReviewShape);
        TextView tvLength = findViewById(R.id.tvReviewLength);
        TextView tvColor = findViewById(R.id.tvReviewColor);
        TextView tvFinish = findViewById(R.id.tvReviewFinish);
        TextView tvAddon = findViewById(R.id.tvReviewAddon);
        TextView tvTotalPrice = findViewById(R.id.tvTotalPrice);
        Button btnAddToCart = findViewById(R.id.btnAddToCart);

        // Aksi Kembali ke Step Sebelumnya
        btnBack.setOnClickListener(v -> finish());

        // 1. Ambil Paket Estafet Data Lengkap dari Step 1 sampai Step 3
        if (getIntent().getExtras() != null) {
            String shape = getIntent().getStringExtra("EXTRA_SHAPE");
            String length = getIntent().getStringExtra("EXTRA_LENGTH");
            String colorType = getIntent().getStringExtra("EXTRA_COLOR_TYPE");
            String colorHex = getIntent().getStringExtra("EXTRA_COLOR_HEX");
            String finishType = getIntent().getStringExtra("EXTRA_FINISH");
            String addon = getIntent().getStringExtra("EXTRA_ADDON");

            // 2. Tampilkan Data di Komponen Teks Ringkasan
            tvShape.setText("Shape: " + shape);
            tvLength.setText("Length: " + length);
            tvColor.setText("Color Type: " + colorType + " (" + colorHex + ")");
            tvFinish.setText("Finish: " + finishType);
            tvAddon.setText("Add-ons: " + addon);

            // Kalkulasi harga dinamis sederhana (jika ada add-on, harga bertambah)
            if (addon != null && !addon.equals("None")) {
                tvTotalPrice.setText("Rp 125.000"); // Base 95k + Add-on 30k sesuai gambar UI
            } else {
                tvTotalPrice.setText("Rp 95.000");
            }
        }

        // 3. Aksi Tombol Akhir: Masuk Keranjang Belanja
        btnAddToCart.setOnClickListener(v -> {
            Toast.makeText(CustomNailReviewActivity.this, "Pesanan kuku kustom berhasil ditambahkan ke Cart!", Toast.LENGTH_LONG).show();

            // Logika lanjutan: Kamu bisa mengarahkan user kembali ke halaman Home/Explore utama
            // Intent intent = new Intent(CustomNailReviewActivity.this, ExploreActivity.class);
            // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            // startActivity(intent);
        });
    }
}