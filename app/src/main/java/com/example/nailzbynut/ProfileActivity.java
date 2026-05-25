package com.example.nailzbynut;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

public class ProfileActivity extends AppCompatActivity {

    private LinearLayout menuContainer;
    private LinearLayout navHome, navHistory, navProfile;
    private TextView tvProfileName;

    private Typeface poppinsRegular, poppinsMedium, poppinsSemiBold, fredoka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Inisialisasi Kontainer Menu List dan Navigasi Bawah
        menuContainer = findViewById(R.id.menuContainer);
        tvProfileName = findViewById(R.id.tv_profile_name);
        navHome = findViewById(R.id.nav_home);
        navHistory = findViewById(R.id.nav_history);
        navProfile = findViewById(R.id.nav_profile);

        // Memuat Resource Font Resmi Proyekmu
        poppinsRegular = ResourcesCompat.getFont(this, R.font.poppins_regular);
        poppinsMedium = ResourcesCompat.getFont(this, R.font.poppins_medium);
        poppinsSemiBold = ResourcesCompat.getFont(this, R.font.poppins_bold);
        fredoka = ResourcesCompat.getFont(this, R.font.fredoka_bold);

        // ====================================================================
        // SINKRONISASI DATA LOG-IN USER (GANTI DINAMIS)
        // ====================================================================
        String sessionUser = getIntent().getStringExtra("USER_NAME");
        if (sessionUser != null && !sessionUser.isEmpty()) {
            tvProfileName.setText(sessionUser);
        } else {
            tvProfileName.setText("Nut"); // Cadangan teks aslimu jika intent kosong
        }
        // ====================================================================

        // Alur Navigasi Klik Bottom Bar Menu Melayang
        if (navHome != null) {
            navHome.setOnClickListener(v -> {
                startActivity(new Intent(ProfileActivity.this, HomeActivity.class));
                overridePendingTransition(0, 0);
                finish();
            });
        }

        if (navHistory != null) {
            navHistory.setOnClickListener(v -> {
                startActivity(new Intent(ProfileActivity.this, HistoryActivity.class));
                overridePendingTransition(0, 0);
                finish();
            });
        }

        // Cetak Deretan Menu List Secara Dinamis Sesuai Mintamu
        addMenu("My Bookings");
        addMenu("Wishlist");
        addMenu("My Reviews");
        addMenu("Promo & Offers");
        addMenu("Payment Methods");
        addMenu("Addresses");
        addMenu("Settings");
        addMenu("Help & Support");
    }

    // Fungsi pembuat baris list item menu dari AI sebelah yang sudah diselaraskan
    private void addMenu(String title) {
        LinearLayout menuLayout = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.topMargin = 25;
        menuLayout.setLayoutParams(params);
        menuLayout.setOrientation(LinearLayout.HORIZONTAL);
        menuLayout.setGravity(Gravity.CENTER_VERTICAL);

        // Aktifkan properti sentuh agar list menu bisa diklik konsumen
        menuLayout.setClickable(true);
        menuLayout.setFocusable(true);

        TextView tvTitle = new TextView(this);
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1
        );
        tvTitle.setLayoutParams(textParams);
        tvTitle.setText(title);
        tvTitle.setTextSize(14);
        tvTitle.setTextColor(Color.parseColor("#444444"));
        tvTitle.setTypeface(poppinsMedium);

        TextView arrow = new TextView(this);
        arrow.setText(">");
        arrow.setTextSize(16);
        arrow.setTextColor(Color.parseColor("#999999"));

        menuLayout.addView(tvTitle);
        menuLayout.addView(arrow);

        // KHUSUS MENU "My Bookings": Jika diklik, otomatis pindah ke halaman History!
        if (title.equals("My Bookings")) {
            menuLayout.setOnClickListener(v -> {
                Intent intent = new Intent(ProfileActivity.this, HistoryActivity.class);
                startActivity(intent);
            });
        } else {
            // Untuk menu lainnya cukup berikan efek toast pesan pop-up ringan
            menuLayout.setOnClickListener(v -> {
                Toast.makeText(ProfileActivity.this, "Membuka menu: " + title, Toast.LENGTH_SHORT).show();
            });
        }

        menuContainer.addView(menuLayout);
    }
}