package com.example.nailzbynut;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation);

        // 1. Ambil nama kiriman dari Login
        if (getIntent().getStringExtra("USER_NAME") != null && !getIntent().getStringExtra("USER_NAME").isEmpty()) {
            currentUsername = getIntent().getStringExtra("USER_NAME");
        }

        // 2. Koneksikan ID XML asli dengan Java
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

        // 3. Ambil data Email Registrasi asli dari memori lokal HP
        SharedPreferences sharedPref = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String savedEmail = sharedPref.getString("SAVED_EMAIL", "deviyanti@gmail.com");

        // 4. Set teks data secara dinamis ke halaman
        if (tvWelcomeUser != null) tvWelcomeUser.setText("Hi, " + currentUsername + "! 👋");
        if (tvProfileName != null) tvProfileName.setText(currentUsername);
        if (tvProfileEmail != null) tvProfileEmail.setText(savedEmail);

        // 5. Logika klik pengerjaan menu kuku (Mengarahkan Press On ke CustomNailShapeActivity)
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

        // 6. HIDUPKAN NAVIGASI BOTTOM BAR
        if (bottomNavigation != null) {
            bottomNavigation.setOnItemSelectedListener(item -> {
                int id = item.getItemId();

                if (id == R.id.nav_home) {
                    layoutHomePage.setVisibility(View.VISIBLE);
                    layoutProfilePage.setVisibility(View.GONE);
                    return true;
                } else if (id == R.id.nav_explore) {
                    // PERBAIKAN: Mengganti ExploreActivity yang eror menjadi pemberitahuan Toast
                    Toast.makeText(MainNavigationActivity.this, "Explore Menu Clicked! ✨", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (id == R.id.nav_profile) {
                    layoutHomePage.setVisibility(View.GONE);
                    layoutProfilePage.setVisibility(View.VISIBLE);
                    return true;
                }
                return false;
            });
        }

        // 7. Logika Tombol Keluar Akun
        if (btnLogout != null) {
            btnLogout.setOnClickListener(v -> {
                Intent intent = new Intent(MainNavigationActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            });
        }
    }
}