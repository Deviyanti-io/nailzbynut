package com.example.nailzbynut;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btnNavExplore = findViewById(R.id.btnNavExplore);
        Button btnNavCustom = findViewById(R.id.btnNavCustom);
        Button btnShopBanner = findViewById(R.id.btnShopBanner);

        // Hubungkan ke Halaman Katalog/Explore (yang berisi 24 gambar kuku)
        btnNavExplore.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ExploreActivity.class);
            startActivity(intent);
        });

        // Hubungkan ke Halaman Custom Nail Step 1 (Shape & Length)
        btnNavCustom.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CustomNailShapeActivity.class);
            startActivity(intent);
        });

        // Banner juga bisa langsung diarahkan ke Custom Nail / Detail Produk
        btnShopBanner.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CustomNailShapeActivity.class);
            startActivity(intent);
        });
    }
}