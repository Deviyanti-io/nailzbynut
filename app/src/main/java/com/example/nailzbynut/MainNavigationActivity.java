package com.example.nailzbynut;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainNavigationActivity extends AppCompatActivity {

    private LinearLayout btnNavHome, btnNavExplore, btnNavProfile;
    private String currentUsername = "Putri Deviyanti";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation);

        // Ambil nama dari LoginActivity
        if (getIntent().getStringExtra("USER_NAME") != null && !getIntent().getStringExtra("USER_NAME").isEmpty()) {
            currentUsername = getIntent().getStringExtra("USER_NAME");
        }

        // Hubungkan ID komponen menu bawah dari activity_main_navigation.xml
        btnNavHome = findViewById(R.id.btn_nav_home);
        btnNavExplore = findViewById(R.id.btn_nav_explore);
        btnNavProfile = findViewById(R.id.btn_nav_profile);

        // Muat HomeFragment secara otomatis di awal masuk aplikasi
        if (savedInstanceState == null) {
            HomeFragment homeFragment = new HomeFragment();
            Bundle bundle = new Bundle();
            bundle.putString("NAME_BUNDLE", currentUsername);
            homeFragment.setArguments(bundle);
            loadFragment(homeFragment);
        }

        // Aksi klik menu Home
        if (btnNavHome != null) {
            btnNavHome.setOnClickListener(v -> {
                HomeFragment hFrag = new HomeFragment();
                Bundle b = new Bundle();
                b.putString("NAME_BUNDLE", currentUsername);
                hFrag.setArguments(b);
                loadFragment(hFrag);
            });
        }

        // Aksi klik menu Explore
        if (btnNavExplore != null) {
            btnNavExplore.setOnClickListener(v -> loadFragment(new ExploreFragment()));
        }

        // Aksi klik menu Profile
        if (btnNavProfile != null) {
            btnNavProfile.setOnClickListener(v -> loadFragment(new ProfileFragment()));
        }
    }

    private void loadFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            if (findViewById(R.id.fragment_container) != null) {
                ft.replace(R.id.fragment_container, fragment);
                ft.commit();
            }
        }
    }
}