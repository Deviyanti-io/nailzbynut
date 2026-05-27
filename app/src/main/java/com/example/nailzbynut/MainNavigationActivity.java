package com.example.nailzbynut;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainNavigationActivity extends AppCompatActivity {

    private ScrollView layoutHomePage;
    private LinearLayout layoutProfilePage;
    private TextView tvWelcomeUser, tvProfileName, tvProfileEmail;
    private CardView menuGelNail, menuPressOnNail, menuManicure;
    private BottomNavigationView bottomNavigation;
    private Button btnLogout;
    private String currentUsername = "Putri Deviyanti";
    private LinearLayout profileContentContainer; // container untuk konten profile tambahan

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation);

        if (getIntent().getStringExtra("USER_NAME") != null && !getIntent().getStringExtra("USER_NAME").isEmpty()) {
            currentUsername = getIntent().getStringExtra("USER_NAME");
        }

        layoutHomePage = findViewById(R.id.layout_home_page);
        layoutProfilePage = findViewById(R.id.layout_profile_page);
        tvWelcomeUser = findViewById(R.id.tv_welcome_user);
        tvProfileName = findViewById(R.id.tv_profile_name);
        tvProfileEmail = findViewById(R.id.tv_profile_email);

        menuGelNail = findViewById(R.id.menu_gel_nail);
        menuPressOnNail = findViewById(R.id.menu_press_on_nail);
        menuManicure = findViewById(R.id.menu_manicure);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        btnLogout = findViewById(R.id.btn_logout);

        SharedPreferences sharedPref = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String savedEmail = sharedPref.getString("SAVED_EMAIL", "deviyanti@gmail.com");

        if (tvWelcomeUser != null) tvWelcomeUser.setText("Hi, " + currentUsername + "! 👋");
        if (tvProfileName != null) tvProfileName.setText(currentUsername);
        if (tvProfileEmail != null) tvProfileEmail.setText(savedEmail);

        // === TAMBAHKAN KONTEN PROFILE LENGKAP (Gold Member, Menu dll) ===
        profileContentContainer = new LinearLayout(this);
        profileContentContainer.setOrientation(LinearLayout.VERTICAL);
        profileContentContainer.setPadding(0, 24, 0, 0);
        addProfileExtras();
        // Masukkan ke layout_profile_page setelah tvProfileEmail
        LinearLayout profileRoot = findViewById(R.id.layout_profile_page);
        if (profileRoot != null) {
            // cari indeks setelah tvProfileEmail
            int index = profileRoot.indexOfChild(tvProfileEmail);
            if (index != -1) {
                profileRoot.addView(profileContentContainer, index + 1);
            } else {
                profileRoot.addView(profileContentContainer);
            }
        }

        if (menuPressOnNail != null) {
            menuPressOnNail.setOnClickListener(v -> {
                Intent intent = new Intent(MainNavigationActivity.this, CustomNailShapeActivity.class);
                startActivity(intent);
            });
        }

        if (menuGelNail != null) {
            menuGelNail.setOnClickListener(v -> {
                Toast.makeText(MainNavigationActivity.this, "Gel Nail service selected!", Toast.LENGTH_SHORT).show();
            });
        }

        if (menuManicure != null) {
            menuManicure.setOnClickListener(v -> {
                Toast.makeText(MainNavigationActivity.this, "Manicure service selected!", Toast.LENGTH_SHORT).show();
            });
        }

        if (bottomNavigation != null) {
            bottomNavigation.setOnItemSelectedListener(item -> {
                int id = item.getItemId();

                if (id == R.id.nav_home) {
                    layoutHomePage.setVisibility(View.VISIBLE);
                    layoutProfilePage.setVisibility(View.GONE);
                    return true;
                } else if (id == R.id.nav_explore) {
                    // PERBAIKAN: Buka ExploreActivity
                    Intent intent = new Intent(MainNavigationActivity.this, ExploreActivity.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.nav_history) {
                    Intent intent = new Intent(MainNavigationActivity.this, HistoryActivity.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.nav_profile) {
                    layoutHomePage.setVisibility(View.GONE);
                    layoutProfilePage.setVisibility(View.VISIBLE);
                    return true;
                }
                return false;
            });
        }

        if (btnLogout != null) {
            btnLogout.setOnClickListener(v -> {
                Intent intent = new Intent(MainNavigationActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            });
        }
    }

    private void addProfileExtras() {
        // Gold Member Card
        CardView goldCard = new CardView(this);
        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        cardParams.setMargins(0, 24, 0, 24);
        goldCard.setLayoutParams(cardParams);
        goldCard.setRadius(20f);
        goldCard.setCardElevation(4f);
        goldCard.setCardBackgroundColor(Color.parseColor("#FFD700"));

        LinearLayout cardContent = new LinearLayout(this);
        cardContent.setOrientation(LinearLayout.VERTICAL);
        cardContent.setPadding(24, 20, 24, 20);
        TextView tvMemberStatus = new TextView(this);
        tvMemberStatus.setText("Gold Member");
        tvMemberStatus.setTextColor(Color.WHITE);
        tvMemberStatus.setTextSize(18);
        tvMemberStatus.setTypeface(tvMemberStatus.getTypeface(), android.graphics.Typeface.BOLD);
        TextView tvPoints = new TextView(this);
        tvPoints.setText("1.250 pts");
        tvPoints.setTextColor(Color.WHITE);
        tvPoints.setTextSize(18);
        tvPoints.setTypeface(tvPoints.getTypeface(), android.graphics.Typeface.BOLD);
        tvPoints.setGravity(Gravity.END);
        TextView tvVoucher = new TextView(this);
        tvVoucher.setText("Kamu punya 5 voucher gratis bulan ini! ✨");
        tvVoucher.setTextColor(Color.WHITE);
        tvVoucher.setTextSize(12);
        tvVoucher.setPadding(0, 8, 0, 0);
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.addView(tvMemberStatus);
        row.addView(tvPoints, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        cardContent.addView(row);
        cardContent.addView(tvVoucher);
        goldCard.addView(cardContent);
        profileContentContainer.addView(goldCard);

        // Menu items dengan desain lebih rapi
        String[] menuNames = {"My Profile", "My Bookings", "Vouchers & Promo", "Settings"};
        for (String name : menuNames) {
            TextView menuItem = new TextView(this);
            menuItem.setText(name);
            menuItem.setPadding(16, 20, 16, 20);
            menuItem.setTextSize(15);
            menuItem.setTextColor(Color.parseColor("#222222"));
            menuItem.setTypeface(menuItem.getTypeface(), android.graphics.Typeface.NORMAL);
            // Tambahkan background selector
            menuItem.setBackgroundResource(android.R.drawable.list_selector_background);
            if (name.equals("My Bookings")) {
                menuItem.setOnClickListener(v -> startActivity(new Intent(this, HistoryActivity.class)));
            }
            profileContentContainer.addView(menuItem);
            View divider = new View(this);
            divider.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1));
            divider.setBackgroundColor(Color.parseColor("#EEEEEE"));
            profileContentContainer.addView(divider);
        }
    }
}

