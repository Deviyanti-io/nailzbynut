package com.example.nailzbynut;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;

public class HistoryActivity extends AppCompatActivity {

    private LinearLayout bookingContainer;
    private LinearLayout navHome, navHistory, navProfile;
    private ImageView btnBackHistory;

    private Typeface poppinsRegular, poppinsMedium, poppinsSemiBold, fredoka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // Menghubungkan ID komponen sesuai XML
        bookingContainer = findViewById(R.id.bookingContainer);
        btnBackHistory = findViewById(R.id.btn_back_history);
        navHome = findViewById(R.id.nav_home);
        navHistory = findViewById(R.id.nav_history);
        navProfile = findViewById(R.id.nav_profile);

        // Memuat Font Resmi Proyek
        poppinsRegular = ResourcesCompat.getFont(this, R.font.poppins_regular);
        poppinsMedium = ResourcesCompat.getFont(this, R.font.poppins_medium);
        poppinsSemiBold = ResourcesCompat.getFont(this, R.font.poppins_bold);
        fredoka = ResourcesCompat.getFont(this, R.font.fredoka_bold);

        // Klik tombol back kembali ke Home
        if (btnBackHistory != null) {
            btnBackHistory.setOnClickListener(v -> {
                startActivity(new Intent(HistoryActivity.this, MainNavigationActivity.class));
                overridePendingTransition(0, 0);
                finish();
            });
        }

        // Navigasi Bottom Bar
        if (navHome != null) {
            navHome.setOnClickListener(v -> {
                startActivity(new Intent(HistoryActivity.this, MainNavigationActivity.class));
                overridePendingTransition(0, 0);
                finish();
            });
        }

        if (navProfile != null) {
            navProfile.setOnClickListener(v -> {
                startActivity(new Intent(HistoryActivity.this, ProfileActivity.class));
                overridePendingTransition(0, 0);
                finish();
            });
        }

        // MEMUAT DATA PERSIS SEPERTI DI SCREENSHOT CONTOH KAMU
        addBookingCard(
                "12",
                "JUN",
                "Custom Nail",
                "Almond • Medium • Lilac Bloom",
                "14:00 - 15:30",
                "Nut (Top Artist)",
                "Confirmed"
        );

        addBookingCard(
                "28",
                "JUN",
                "Gel Nail",
                "Glazed Pink",
                "11:00 - 12:30",
                "Nut (Top Artist)",
                "Confirmed"
        );

        addBookingCard(
                "10",
                "JUL",
                "Manicure",
                "Basic Manicure",
                "15:00 - 16:00",
                "Nut (Top Artist)",
                "Pending"
        );
    }

    // Pembuatan struktur kartu dinamis murni mengikuti screenshot (Tanpa Gambar Kuku)
    private void addBookingCard(String date, String month, String title, String subtitle, String time, String artist, String status) {

        CardView cardView = new CardView(this);
        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        cardParams.setMargins(0, 0, 0, 32); // Jarak antar kartu riwayat
        cardView.setLayoutParams(cardParams);
        cardView.setRadius(32);
        cardView.setCardElevation(2);
        cardView.setCardBackgroundColor(Color.WHITE);

        // Layout Utama di dalam kartu (Orientasi Vertikal agar Tombol View Details berada di bawah)
        LinearLayout cardContentRoot = new LinearLayout(this);
        cardContentRoot.setOrientation(LinearLayout.VERTICAL);
        cardContentRoot.setPadding(24, 24, 24, 24);

        // Layout Baris Atas (Kotak Tanggal + Teks Informasi + Status)
        LinearLayout topRowLayout = new LinearLayout(this);
        topRowLayout.setOrientation(LinearLayout.HORIZONTAL);
        topRowLayout.setGravity(Gravity.TOP);

        // 1. KOTAK TANGGAL KIRI (Sesuai Desain Screenshot)
        LinearLayout dateBox = new LinearLayout(this);
        LinearLayout.LayoutParams dateParams = new LinearLayout.LayoutParams(130, 160);
        dateParams.rightMargin = 28;
        dateBox.setLayoutParams(dateParams);
        dateBox.setOrientation(LinearLayout.VERTICAL);
        dateBox.setGravity(Gravity.CENTER);

        // Membuat background kotak tanggal melengkung halus berwarna ungu muda transparan
        GradientDrawable dateBoxBg = new GradientDrawable();
        dateBoxBg.setColor(Color.parseColor("#F1EFFF"));
        dateBoxBg.setCornerRadius(16);
        dateBox.setBackground(dateBoxBg);

        TextView tvDate = new TextView(this);
        tvDate.setText(date);
        tvDate.setTextSize(22);
        tvDate.setTextColor(Color.parseColor("#7A75F0"));
        tvDate.setTypeface(fredoka);

        TextView tvMonth = new TextView(this);
        tvMonth.setText(month);
        tvMonth.setTextSize(11);
        tvMonth.setTextColor(Color.parseColor("#7A75F0"));
        tvMonth.setTypeface(poppinsMedium);

        dateBox.addView(tvDate);
        dateBox.addView(tvMonth);

        // 2. AREA TEKS INFORMASI TENGAH
        LinearLayout textInfoLayout = new LinearLayout(this);
        textInfoLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams textInfoParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        textInfoLayout.setLayoutParams(textInfoParams);

        TextView tvTitle = new TextView(this);
        tvTitle.setText(title);
        tvTitle.setTextSize(15);
        tvTitle.setTextColor(Color.parseColor("#222222"));
        tvTitle.setTypeface(poppinsSemiBold);

        TextView tvSubtitle = new TextView(this);
        tvSubtitle.setText(subtitle);
        tvSubtitle.setTextSize(12);
        tvSubtitle.setTextColor(Color.parseColor("#8A8A93"));
        tvSubtitle.setTypeface(poppinsRegular);
        tvSubtitle.setPadding(0, 2, 0, 0);

        // Baris Jam Kerja (Ditambahkan Ikon teks bulat kecil sebagai pengganti drawable)
        TextView tvTime = new TextView(this);
        tvTime.setText("🕒  " + time);
        tvTime.setTextSize(12);
        tvTime.setTextColor(Color.parseColor("#666666"));
        tvTime.setPadding(0, 12, 0, 0);
        tvTime.setTypeface(poppinsRegular);

        // Baris Nama Artist Salon
        TextView tvArtist = new TextView(this);
        tvArtist.setText("👤  " + artist);
        tvArtist.setTextSize(12);
        tvArtist.setTextColor(Color.parseColor("#666666"));
        tvArtist.setPadding(0, 4, 0, 0);
        tvArtist.setTypeface(poppinsRegular);

        textInfoLayout.addView(tvTitle);
        textInfoLayout.addView(tvSubtitle);
        textInfoLayout.addView(tvTime);
        textInfoLayout.addView(tvArtist);

        // 3. TEKS STATUS DI POJOK KANAN ATAS
        TextView tvStatus = new TextView(this);
        tvStatus.setText(status);
        tvStatus.setTextSize(12);
        tvStatus.setTypeface(poppinsSemiBold);

        if (status.equals("Confirmed")) {
            tvStatus.setTextColor(Color.parseColor("#4CAF50")); // Hijau Terkonfirmasi
        } else {
            tvStatus.setTextColor(Color.parseColor("#FF9800")); // Oranye Pending
        }

        // Gabungkan baris atas
        topRowLayout.addView(dateBox);
        topRowLayout.addView(textInfoLayout);
        topRowLayout.addView(tvStatus);

        // 4. TOMBOL VIEW DETAILS DI BAGIAN BAWAH KARTU (Persis Sesuai Screenshot)
        TextView btnViewDetails = new TextView(this);
        LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                76
        );
        btnParams.topMargin = 24;
        btnViewDetails.setLayoutParams(btnParams);
        btnViewDetails.setText("View Details");
        btnViewDetails.setTextSize(12);
        btnViewDetails.setTextColor(Color.parseColor("#7A75F0"));
        btnViewDetails.setTypeface(poppinsMedium);
        btnViewDetails.setGravity(Gravity.CENTER);

        // Membuat background tombol memanjang bulat lonjong ungu muda transparan halus
        GradientDrawable btnBg = new GradientDrawable();
        btnBg.setColor(Color.parseColor("#F5F2FF"));
        btnBg.setCornerRadius(38);
        btnViewDetails.setBackground(btnBg);

        // Masukkan baris atas dan tombol bawah ke dalam struktur utama kartu
        cardContentRoot.addView(topRowLayout);
        cardContentRoot.addView(btnViewDetails);
        cardView.addView(cardContentRoot);

        // Masukkan kartu yang sudah jadi ke dalam container layout halaman XML kamu
        bookingContainer.addView(cardView);
    }
}