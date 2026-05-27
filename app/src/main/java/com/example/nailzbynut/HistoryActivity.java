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
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private LinearLayout bookingContainer;
    private BottomNavigationView bottomNavigation;
    private TextView tabUpcoming, tabCompleted;
    private Typeface poppinsRegular, poppinsMedium, poppinsSemiBold, fredoka;
    private String currentTab = "Upcoming";

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
        SharedPreferences prefs = getSharedPreferences("FinalBookingData", MODE_PRIVATE);
        String json = prefs.getString("bookings_list", "[]");
        try {
            JSONArray all = new JSONArray(json);
            List<JSONObject> filtered = new ArrayList<>();
            List<Integer> indices = new ArrayList<>();
            for (int i = 0; i < all.length(); i++) {
                JSONObject obj = all.getJSONObject(i);
                String status = obj.optString("status");
                if (currentTab.equals("Upcoming") && status.equals("Confirmed")) {
                    filtered.add(obj);
                    indices.add(i);
                } else if (currentTab.equals("Completed") && status.equals("Completed")) {
                    filtered.add(obj);
                    indices.add(i);
                }
            }
            if (filtered.isEmpty()) {
                TextView empty = new TextView(this);
                empty.setText("Tidak ada booking " + currentTab.toLowerCase());
                empty.setGravity(Gravity.CENTER);
                empty.setPadding(0, 100, 0, 0);
                empty.setTextColor(Color.GRAY);
                bookingContainer.addView(empty);
            } else {
                for (int i = 0; i < filtered.size(); i++) {
                    addCard(filtered.get(i), indices.get(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error loading: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void addCard(JSONObject booking, int index) {
        try {
            String date = booking.getString("date");
            String month = booking.getString("month");
            String title = booking.getString("title");
            String subtitle = booking.getString("subtitle");
            String time = booking.getString("time");
            String artist = booking.getString("artist");
            String status = booking.getString("status");
            String colorHex = booking.optString("colorHex", "#D6001C");

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
            content.setPadding(24, 24, 24, 24);

            // Baris atas: tanggal, info, status
            LinearLayout topRow = new LinearLayout(this);
            topRow.setOrientation(LinearLayout.HORIZONTAL);
            topRow.setGravity(Gravity.TOP);

            // Kotak tanggal
            LinearLayout dateBox = new LinearLayout(this);
            LinearLayout.LayoutParams dateParams = new LinearLayout.LayoutParams(120, 140);
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

            // Info tengah
            LinearLayout info = new LinearLayout(this);
            info.setOrientation(LinearLayout.VERTICAL);
            info.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
            TextView tvTitle = new TextView(this);
            tvTitle.setText(title);
            tvTitle.setTextSize(15);
            tvTitle.setTypeface(poppinsSemiBold);
            tvTitle.setTextColor(Color.parseColor("#222222"));
            TextView tvSub = new TextView(this);
            tvSub.setText(subtitle);
            tvSub.setTextSize(12);
            tvSub.setTextColor(Color.parseColor("#8A8A93"));
            tvSub.setTypeface(poppinsRegular);
            // Lingkaran warna
            View colorIndicator = new View(this);
            LinearLayout.LayoutParams colorParams = new LinearLayout.LayoutParams(24, 24);
            colorParams.setMargins(0, 8, 0, 8);
            colorIndicator.setLayoutParams(colorParams);
            GradientDrawable colorCircle = new GradientDrawable();
            colorCircle.setShape(GradientDrawable.OVAL);
            colorCircle.setColor(Color.parseColor(colorHex));
            colorIndicator.setBackground(colorCircle);
            TextView tvTime = new TextView(this);
            tvTime.setText("🕒 " + time);
            tvTime.setTextSize(12);
            tvTime.setTextColor(Color.parseColor("#666666"));
            tvTime.setTypeface(poppinsRegular);
            TextView tvArtist = new TextView(this);
            tvArtist.setText("👤 " + artist);
            tvArtist.setTextSize(12);
            tvArtist.setTextColor(Color.parseColor("#666666"));
            tvArtist.setTypeface(poppinsRegular);
            info.addView(tvTitle);
            info.addView(tvSub);
            info.addView(colorIndicator);
            info.addView(tvTime);
            info.addView(tvArtist);

            TextView tvStatus = new TextView(this);
            tvStatus.setText(status);
            tvStatus.setTextSize(12);
            tvStatus.setTypeface(poppinsSemiBold);
            tvStatus.setTextColor(status.equals("Confirmed") ? Color.parseColor("#4CAF50") : Color.parseColor("#FF9800"));

            topRow.addView(dateBox);
            topRow.addView(info);
            topRow.addView(tvStatus);
            content.addView(topRow);

            // Tombol Edit & Hapus
            LinearLayout actions = new LinearLayout(this);
            actions.setOrientation(LinearLayout.HORIZONTAL);
            actions.setPadding(0, 16, 0, 8);
            Button btnEdit = new Button(this);
            LinearLayout.LayoutParams editParams = new LinearLayout.LayoutParams(0, 48, 1);
            editParams.rightMargin = 12;
            btnEdit.setLayoutParams(editParams);
            btnEdit.setText("Edit");
            btnEdit.setTextSize(12);
            btnEdit.setTextColor(Color.parseColor("#7A75F0"));
            btnEdit.setTypeface(poppinsMedium);
            btnEdit.setAllCaps(false);
            GradientDrawable editBg = new GradientDrawable();
            editBg.setColor(Color.parseColor("#F5F2FF"));
            editBg.setCornerRadius(24);
            btnEdit.setBackground(editBg);
            Button btnDelete = new Button(this);
            btnDelete.setLayoutParams(new LinearLayout.LayoutParams(0, 48, 1));
            btnDelete.setText("Hapus");
            btnDelete.setTextSize(12);
            btnDelete.setTextColor(Color.RED);
            btnDelete.setTypeface(poppinsMedium);
            btnDelete.setAllCaps(false);
            GradientDrawable delBg = new GradientDrawable();
            delBg.setColor(Color.parseColor("#FFEBEE"));
            delBg.setCornerRadius(24);
            btnDelete.setBackground(delBg);
            actions.addView(btnEdit);
            actions.addView(btnDelete);
            content.addView(actions);
            card.addView(content);
            bookingContainer.addView(card);

            btnEdit.setOnClickListener(v -> showEditDialog(index, booking));
            btnDelete.setOnClickListener(v -> confirmDelete(index));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showEditDialog(int index, JSONObject booking) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Booking");
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 30, 50, 10);
        EditText etDate = new EditText(this);
        etDate.setHint("Tanggal");
        etDate.setText(booking.optString("date"));
        layout.addView(etDate);
        EditText etMonth = new EditText(this);
        etMonth.setHint("Bulan (JAN, FEB, ...)");
        etMonth.setText(booking.optString("month"));
        layout.addView(etMonth);
        EditText etTime = new EditText(this);
        etTime.setHint("Waktu (contoh: 09.00 - 10.30)");
        etTime.setText(booking.optString("time"));
        layout.addView(etTime);
        EditText etStatus = new EditText(this);
        etStatus.setHint("Status (Confirmed/Completed)");
        etStatus.setText(booking.optString("status"));
        layout.addView(etStatus);
        builder.setView(layout);
        builder.setPositiveButton("Simpan", (dialog, which) -> {
            try {
                booking.put("date", etDate.getText().toString().trim());
                booking.put("month", etMonth.getText().toString().trim());
                booking.put("time", etTime.getText().toString().trim());
                booking.put("status", etStatus.getText().toString().trim());
                updateBooking(index, booking);
            } catch (Exception e) { e.printStackTrace(); }
        });
        builder.setNegativeButton("Batal", null);
        builder.show();
    }

    private void updateBooking(int index, JSONObject updated) {
        SharedPreferences prefs = getSharedPreferences("FinalBookingData", MODE_PRIVATE);
        String json = prefs.getString("bookings_list", "[]");
        try {
            JSONArray arr = new JSONArray(json);
            arr.put(index, updated);
            prefs.edit().putString("bookings_list", arr.toString()).apply();
            Toast.makeText(this, "Booking diperbarui", Toast.LENGTH_SHORT).show();
            loadBookings();
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void confirmDelete(int index) {
        new AlertDialog.Builder(this)
                .setTitle("Hapus Booking")
                .setMessage("Yakin ingin menghapus?")
                .setPositiveButton("Hapus", (dialog, which) -> deleteBooking(index))
                .setNegativeButton("Batal", null)
                .show();
    }

    private void deleteBooking(int index) {
        SharedPreferences prefs = getSharedPreferences("FinalBookingData", MODE_PRIVATE);
        String json = prefs.getString("bookings_list", "[]");
        try {
            JSONArray arr = new JSONArray(json);
            arr.remove(index);
            prefs.edit().putString("bookings_list", arr.toString()).apply();
            Toast.makeText(this, "Booking dihapus", Toast.LENGTH_SHORT).show();
            loadBookings();
        } catch (Exception e) { e.printStackTrace(); }
    }
}