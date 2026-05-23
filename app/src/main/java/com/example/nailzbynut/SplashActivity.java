package com.example.nailzbynut;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private Button btnGetStarted;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Cari ID tombol Get Started yang ada di layout activity_splash.xml kamu
        // Catatan: Pastikan ID di bawah ini (@id/btn_get_started) sama dengan yang ada di XML splash kamu
        btnGetStarted = findViewById(R.id.btn_get_started);

        // Logika: Pindah ke halaman Login HANYA saat tombol diklik
        btnGetStarted.setOnClickListener(v -> {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Tutup halaman splash
        });
    }
}