package com.example.nailzbynut;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    // Deklarasi komponen sesuai ID di file XML kamu
    private TextView tvUserGreeting;
    private LinearLayout btnNavExplore, btnNavCustom;
    private LinearLayout navHome, navHistory, navProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // 1. Inisialisasi komponen murni menembak ID asli dari XML kamu
        tvUserGreeting = findViewById(R.id.tv_user_greeting);
        btnNavExplore = findViewById(R.id.btnNavExplore);
        btnNavCustom = findViewById(R.id.btnNavCustom);

        navHome = findViewById(R.id.nav_home);
        navHistory = findViewById(R.id.nav_history);
        navProfile = findViewById(R.id.nav_profile);

        // ====================================================================
        // LOGIKA AMAN SINKRONISASI DATABASE USER LOGIN
        // ====================================================================
        // Contoh jika kamu melempar nama user dari LoginActivity menggunakan Intent:
        String loggedInUser = getIntent().getStringExtra("USER_NAME");

        if (loggedInUser != null && !loggedInUser.isEmpty()) {
            // Mengubah nama secara dinamis mengikuti user yang login
            tvUserGreeting.setText("Hi, " + loggedInUser + "!");
        } else {
            // Cadangan (Fallback) jika data Intent kosong, tetap memakai teks aslimu
            tvUserGreeting.setText("Hi, Nut!");
        }
        // ====================================================================

        // 2. Aktifkan Klik untuk Menu Layanan Utama
        if (btnNavExplore != null) {
            btnNavExplore.setOnClickListener(v -> {
                Toast.makeText(this, "Membuka Explore List...", Toast.LENGTH_SHORT).show();
                // Jalankan intent ke halaman explore kamu jika sudah siap:
                // Intent intent = new Intent(HomeActivity.this, ExploreActivity.class);
                // startActivity(intent);
            });
        }

        if (btnNavCustom != null) {
            btnNavCustom.setOnClickListener(v -> {
                // Berhasil berpindah langsung ke Step 1: Pilih Bentuk Kuku
                Intent intent = new Intent(HomeActivity.this, CustomNailShapeActivity.class);
                startActivity(intent);
            });
        }

        // 3. Aktifkan Fungsi Klik Penuh untuk Seluruh Bottom Navigation Bar
        if (navHome != null) {
            navHome.setOnClickListener(v -> {
                // Karena kita sudah berada di Home, cukup berikan feedback atau refresh data
                Toast.makeText(this, "Kamu berada di Beranda", Toast.LENGTH_SHORT).show();
            });
        }

        if (navHistory != null) {
            navHistory.setOnClickListener(v -> {
                // Berpindah masuk ke halaman History
                Intent intent = new Intent(HomeActivity.this, HistoryActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0); // Efek transisi instan agar tidak patah-patah
                finish(); // Tutup halaman beranda agar tumpukan activity rapi
            });
        }

        if (navProfile != null) {
            navProfile.setOnClickListener(v -> {
                // Berpindah masuk ke halaman Profile
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            });
        }
    }
}