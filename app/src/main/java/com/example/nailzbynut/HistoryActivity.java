package com.example.nailzbynut;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private LinearLayout bookingContainer;
    private BottomNavigationView bottomNavigation;
    private TextView tabUpcoming, tabCompleted;
    private Typeface poppinsRegular, poppinsMedium, poppinsSemiBold, fredoka;
    private String currentTab = "Upcoming";
    private List<String> bookingIds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        bookingContainer = findViewById(R.id.bookingContainer);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        tabUpcoming = findViewById(R.id.tab_upcoming);
        tabCompleted = findViewById(R.id.tab_completed);

        poppinsRegular = ResourcesCompat.getFont(this, R.font.poppins_regular);
        poppinsMedium = ResourcesCompat.getFont(this, R.font.poppins_medium);
        poppinsSemiBold = ResourcesCompat.getFont(this, R.font.poppins_bold);
        fredoka = ResourcesCompat.getFont(this, R.font.fredoka_bold);

        tabUpcoming.setOnClickListener(v -> {
            currentTab = "Upcoming";
            updateTabStyle();
            loadBookings();
        });
        tabCompleted.setOnClickListener(v -> {
            currentTab = "Completed";
            updateTabStyle();
            loadBookings();
        });

        bottomNavigation.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                startActivity(new Intent(this, MainNavigationActivity.class));
                finish();
                return true;
            } else if (id == R.id.nav_explore) {
                startActivity(new Intent(this, ExploreActivity.class));
                return true;
            } else if (id == R.id.nav_history) {
                return true;
            } else if (id == R.id.nav_profile) {
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
            }
            return false;
        });
        bottomNavigation.setSelectedItemId(R.id.nav_history);

        loadBookings();
    }

    private void updateTabStyle() {
        GradientDrawable activeBg = new GradientDrawable();
        activeBg.setColor(Color.parseColor("#EFEFFF"));
        activeBg.setCornerRadius(24f);
        GradientDrawable inactiveBg = new GradientDrawable();
        inactiveBg.setColor(Color.TRANSPARENT);
        inactiveBg.setCornerRadius(24f);

        if (currentTab.equals("Upcoming")) {
            tabUpcoming.setBackground(activeBg);
            tabUpcoming.setTextColor(Color.parseColor("#7A75F0"));
            tabCompleted.setBackground(inactiveBg);
            tabCompleted.setTextColor(Color.parseColor("#B0B0B0"));
        } else {
            tabCompleted.setBackground(activeBg);
            tabCompleted.setTextColor(Color.parseColor("#7A75F0"));
            tabUpcoming.setBackground(inactiveBg);
            tabUpcoming.setTextColor(Color.parseColor("#B0B0B0"));
        }
    }

    private void loadBookings() {
        bookingContainer.removeAllViews();
        SharedPreferences prefs = getSharedPreferences("SimpleBookingData", MODE_PRIVATE);
        String idsString = prefs.getString("booking_ids", "");
        bookingIds.clear();
        if (!idsString.isEmpty()) {
            String[] ids = idsString.split(",");
            for (String id : ids) {
                bookingIds.add(id);
            }
        }

        if (bookingIds.isEmpty()) {
            TextView empty = new TextView(this);
            empty.setText("Tidak ada booking " + currentTab.toLowerCase());
            empty.setGravity(Gravity.CENTER);
            empty.setPadding(0, 100, 0, 0);
            empty.setTextColor(Color.GRAY);
            bookingContainer.addView(empty);
            return;
        }

        // Filter berdasarkan status
        for (int i = 0; i < bookingIds.size(); i++) {
            String id = bookingIds.get(i);
            String status = prefs.getString(id + "_status", "");
            if (currentTab.equals("Upcoming") && status.equals("Confirmed")) {
                addCard(id, i);
            } else if (currentTab.equals("Completed") && status.equals("Completed")) {
                addCard(id, i);
            }
        }
    }

    private void addCard(String bookingId, int index) {
        SharedPreferences prefs = getSharedPreferences("SimpleBookingData", MODE_PRIVATE);
        String date = prefs.getString(bookingId + "_date", "01");
        String month = prefs.getString(bookingId + "_month", "JAN");
        String title = prefs.getString(bookingId + "_title", "Custom Nail");
        String subtitle = prefs.getString(bookingId + "_subtitle", "Almond • Medium");
        String time = prefs.getString(bookingId + "_time", "09:00");
        String artist = prefs.getString(bookingId + "_artist", "Nut (Top Artist)");
        String status = prefs.getString(bookingId + "_status", "Confirmed");
        String colorHex = prefs.getString(bookingId + "_colorHex", "#D6001C");

        // DEBUG Toast
        Toast.makeText(this, "Warna: " + colorHex, Toast.LENGTH_SHORT).show();

        CardView card = new CardView(this);
        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        cardParams.setMargins(0, 0, 0, 24);
        card.setLayoutParams(cardParams);
        card.setRadius(32);
        card.setCardElevation(2);
        card.setCardBackgroundColor(Color.WHITE);

        LinearLayout content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setPadding(32, 32, 32, 32); // Diperlebar sedikit pading card agar lebih lega

        LinearLayout topRow = new LinearLayout(this);
        topRow.setOrientation(LinearLayout.HORIZONTAL);
        topRow.setGravity(Gravity.TOP);

        // Kotak tanggal
        LinearLayout dateBox = new LinearLayout(this);
        LinearLayout.LayoutParams dateParams = new LinearLayout.LayoutParams(100, 120);
        dateParams.rightMargin = 24;
        dateBox.setLayoutParams(dateParams);
        dateBox.setOrientation(LinearLayout.VERTICAL);
        dateBox.setGravity(Gravity.CENTER);
        GradientDrawable dateBg = new GradientDrawable();
        dateBg.setColor(Color.parseColor("#F1EFFF"));
        dateBg.setCornerRadius(16);
        dateBox.setBackground(dateBg);

        TextView tvDate = new TextView(this);
        tvDate.setText(date);
        tvDate.setTextSize(20);
        tvDate.setTextColor(Color.parseColor("#7A75F0"));
        tvDate.setTypeface(fredoka);
        tvDate.setGravity(Gravity.CENTER);
        TextView tvMonth = new TextView(this);
        tvMonth.setText(month);
        tvMonth.setTextSize(10);
        tvMonth.setTextColor(Color.parseColor("#7A75F0"));
        tvMonth.setTypeface(poppinsMedium);
        tvMonth.setGravity(Gravity.CENTER);
        dateBox.addView(tvDate);
        dateBox.addView(tvMonth);

        // Info
        LinearLayout info = new LinearLayout(this);
        info.setOrientation(LinearLayout.VERTICAL);
        info.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));

        TextView tvTitle = new TextView(this);
        tvTitle.setText(title);
        tvTitle.setTextSize(14);
        tvTitle.setTypeface(poppinsSemiBold);
        tvTitle.setTextColor(Color.parseColor("#222222"));

        TextView tvSub = new TextView(this);
        tvSub.setText(subtitle);
        tvSub.setTextSize(11);
        tvSub.setTextColor(Color.parseColor("#8A8A93"));
        tvSub.setTypeface(poppinsRegular);

        // Lingkaran warna
        View colorIndicator = new View(this);
        LinearLayout.LayoutParams colorParams = new LinearLayout.LayoutParams(24, 24); // Sedikit diperbesar dari 20 ke 24 agar jelas
        colorParams.setMargins(0, 8, 0, 8);
        colorIndicator.setLayoutParams(colorParams);
        GradientDrawable colorCircle = new GradientDrawable();
        colorCircle.setShape(GradientDrawable.OVAL);
        colorCircle.setColor(Color.parseColor(colorHex));
        colorIndicator.setBackground(colorCircle);

        TextView tvTime = new TextView(this);
        tvTime.setText("🕒 " + time);
        tvTime.setTextSize(11);
        tvTime.setTextColor(Color.parseColor("#666666"));
        tvTime.setTypeface(poppinsRegular);

        TextView tvArtist = new TextView(this);
        tvArtist.setText("👤 " + artist);
        tvArtist.setTextSize(11);
        tvArtist.setTextColor(Color.parseColor("#666666"));
        tvArtist.setTypeface(poppinsRegular);

        info.addView(tvTitle);
        info.addView(tvSub);
        info.addView(colorIndicator);
        info.addView(tvTime);
        info.addView(tvArtist);

        TextView tvStatus = new TextView(this);
        tvStatus.setText(status);
        tvStatus.setTextSize(11);
        tvStatus.setTypeface(poppinsSemiBold);
        tvStatus.setTextColor(status.equals("Confirmed") ? Color.parseColor("#4CAF50") : Color.parseColor("#FF9800"));

        topRow.addView(dateBox);
        topRow.addView(info);
        topRow.addView(tvStatus);
        content.addView(topRow);

        // Tombol Edit & Hapus (Bagian Perbaikan Utama)
        LinearLayout actions = new LinearLayout(this);
        actions.setOrientation(LinearLayout.HORIZONTAL);
        actions.setPadding(0, 24, 0, 8); // Menaikkan padding atas kontainer tombol

        // Perbaikan Tinggi dan Padding Tombol Edit
        Button btnEdit = new Button(this);
        LinearLayout.LayoutParams editParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        editParams.rightMargin = 16;
        btnEdit.setLayoutParams(editParams);
        btnEdit.setText("Edit");
        btnEdit.setTextSize(13);
        btnEdit.setTextColor(Color.parseColor("#7A75F0"));
        btnEdit.setTypeface(poppinsMedium);
        btnEdit.setAllCaps(false);
        btnEdit.setPadding(0, 16, 0, 16); // Memberi ruang vertikal yang pas agar teks tidak tenggelam

        GradientDrawable editBg = new GradientDrawable();
        editBg.setColor(Color.parseColor("#F5F2FF"));
        editBg.setCornerRadius(24);
        btnEdit.setBackground(editBg);

        // Perbaikan Tinggi dan Padding Tombol Hapus
        Button btnDelete = new Button(this);
        LinearLayout.LayoutParams deleteParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        btnDelete.setLayoutParams(deleteParams);
        btnDelete.setText("Hapus");
        btnDelete.setTextSize(13);
        btnDelete.setTextColor(Color.RED);
        btnDelete.setTypeface(poppinsMedium);
        btnDelete.setAllCaps(false);
        btnDelete.setPadding(0, 16, 0, 16); // Mengikuti spesifikasi padding yang sama

        GradientDrawable delBg = new GradientDrawable();
        delBg.setColor(Color.parseColor("#FFEBEE"));
        delBg.setCornerRadius(24);
        btnDelete.setBackground(delBg);

        actions.addView(btnEdit);
        actions.addView(btnDelete);
        content.addView(actions);
        card.addView(content);
        bookingContainer.addView(card);

        btnEdit.setOnClickListener(v -> showEditDialog(bookingId, index));
        btnDelete.setOnClickListener(v -> confirmDelete(bookingId, index));
    }

    private void showEditDialog(String bookingId, int position) {
        SharedPreferences prefs = getSharedPreferences("SimpleBookingData", MODE_PRIVATE);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Booking");
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 30, 50, 10);

        EditText etDate = new EditText(this);
        etDate.setHint("Tanggal");
        etDate.setText(prefs.getString(bookingId + "_date", ""));
        layout.addView(etDate);

        EditText etMonth = new EditText(this);
        etMonth.setHint("Bulan (JAN, FEB, ...)");
        etMonth.setText(prefs.getString(bookingId + "_month", ""));
        layout.addView(etMonth);

        EditText etTime = new EditText(this);
        etTime.setHint("Waktu (contoh: 09.00 - 10.30)");
        etTime.setText(prefs.getString(bookingId + "_time", ""));
        layout.addView(etTime);

        EditText etStatus = new EditText(this);
        etStatus.setHint("Status (Confirmed/Completed)");
        etStatus.setText(prefs.getString(bookingId + "_status", ""));
        layout.addView(etStatus);

        builder.setView(layout);
        builder.setPositiveButton("Simpan", (dialog, which) -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(bookingId + "_date", etDate.getText().toString().trim());
            editor.putString(bookingId + "_month", etMonth.getText().toString().trim());
            editor.putString(bookingId + "_time", etTime.getText().toString().trim());
            editor.putString(bookingId + "_status", etStatus.getText().toString().trim());
            editor.apply();
            Toast.makeText(this, "Booking diperbarui", Toast.LENGTH_SHORT).show();
            loadBookings();
        });
        builder.setNegativeButton("Batal", null);
        builder.show();
    }

    private void confirmDelete(String bookingId, int position) {
        new AlertDialog.Builder(this)
                .setTitle("Hapus Booking")
                .setMessage("Yakin ingin menghapus?")
                .setPositiveButton("Hapus", (dialog, which) -> deleteBooking(bookingId, position))
                .setNegativeButton("Batal", null)
                .show();
    }

    private void deleteBooking(String bookingId, int position) {
        SharedPreferences prefs = getSharedPreferences("SimpleBookingData", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.remove(bookingId + "_date");
        editor.remove(bookingId + "_month");
        editor.remove(bookingId + "_title");
        editor.remove(bookingId + "_subtitle");
        editor.remove(bookingId + "_time");
        editor.remove(bookingId + "_artist");
        editor.remove(bookingId + "_status");
        editor.remove(bookingId + "_colorHex");

        String idsString = prefs.getString("booking_ids", "");
        String[] ids = idsString.split(",");
        StringBuilder newIds = new StringBuilder();
        for (String id : ids) {
            if (!id.equals(bookingId)) {
                if (newIds.length() > 0) newIds.append(",");
                newIds.append(id);
            }
        }
        editor.putString("booking_ids", newIds.toString());
        editor.apply();
        Toast.makeText(this, "Booking dihapus", Toast.LENGTH_SHORT).show();
        loadBookings();
    }
}