package com.example.nailzbynut;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class CustomNailShapeActivity extends AppCompatActivity {

    private ImageView btnBack;
    private LinearLayout btnAlmond, btnCoffin, btnSquare, btnOval, btnStiletto, btnRound;
    private TextView tvAlmond, tvCoffin, tvSquare, tvOval, tvStiletto, tvRound;
    private AppCompatButton btnNext, btnShort, btnMedium, btnLong;

    private String selectedShape = "Almond";
    private String selectedLength = "Medium"; // Default ukuran panjang

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_nail_shape);

        // Inisialisasi Bentuk Kuku
        btnBack = findViewById(R.id.btn_back);
        btnAlmond = findViewById(R.id.btn_shape_almond);
        btnCoffin = findViewById(R.id.btn_shape_coffin);
        btnSquare = findViewById(R.id.btn_shape_square);
        btnOval = findViewById(R.id.btn_shape_oval);
        btnStiletto = findViewById(R.id.btn_shape_stiletto);
        btnRound = findViewById(R.id.btn_shape_round);

        tvAlmond = findViewById(R.id.tv_almond);
        tvCoffin = findViewById(R.id.tv_coffin);
        tvSquare = findViewById(R.id.tv_square);
        tvOval = findViewById(R.id.tv_oval);
        tvStiletto = findViewById(R.id.tv_stiletto);
        tvRound = findViewById(R.id.tv_round);

        // Inisialisasi Ukuran Panjang (Length)
        btnShort = findViewById(R.id.btn_len_short);
        btnMedium = findViewById(R.id.btn_len_medium);
        btnLong = findViewById(R.id.btn_len_long);

        btnNext = findViewById(R.id.btn_next_step);

        btnBack.setOnClickListener(v -> finish());

        // Set keadaan awal dinamis
        changeSelectedState("Almond");
        changeLengthState("Medium");

        // Klik Bentuk Kuku
        btnAlmond.setOnClickListener(v -> changeSelectedState("Almond"));
        btnCoffin.setOnClickListener(v -> changeSelectedState("Coffin"));
        btnSquare.setOnClickListener(v -> changeSelectedState("Square"));
        btnOval.setOnClickListener(v -> changeSelectedState("Oval"));
        btnStiletto.setOnClickListener(v -> changeSelectedState("Stiletto"));
        btnRound.setOnClickListener(v -> changeSelectedState("Round"));

        // Klik Ukuran Panjang
        btnShort.setOnClickListener(v -> changeLengthState("Short"));
        btnMedium.setOnClickListener(v -> changeLengthState("Medium"));
        btnLong.setOnClickListener(v -> changeLengthState("Long"));

        // Pindah halaman bawa data lengkap
        btnNext.setOnClickListener(v -> {
            Intent intent = new Intent(CustomNailShapeActivity.this, CustomNailColorActivity.class);
            intent.putExtra("SHAPE_DATA", selectedShape);
            intent.putExtra("LENGTH_DATA", selectedLength);
            startActivity(intent);
        });
    }

    private void changeSelectedState(String shape) {
        selectedShape = shape;
        btnAlmond.setBackgroundResource(android.R.color.white);
        tvAlmond.setTextColor(Color.parseColor("#1A1C29"));
        btnCoffin.setBackgroundResource(android.R.color.white);
        tvCoffin.setTextColor(Color.parseColor("#1A1C29"));
        btnSquare.setBackgroundResource(android.R.color.white);
        tvSquare.setTextColor(Color.parseColor("#1A1C29"));
        btnOval.setBackgroundResource(android.R.color.white);
        tvOval.setTextColor(Color.parseColor("#1A1C29"));
        btnStiletto.setBackgroundResource(android.R.color.white);
        tvStiletto.setTextColor(Color.parseColor("#1A1C29"));
        btnRound.setBackgroundResource(android.R.color.white);
        tvRound.setTextColor(Color.parseColor("#1A1C29"));

        switch (shape) {
            case "Almond":
                btnAlmond.setBackgroundColor(Color.parseColor("#F4F3FF"));
                tvAlmond.setTextColor(Color.parseColor("#5A55CA"));
                break;
            case "Coffin":
                btnCoffin.setBackgroundColor(Color.parseColor("#F4F3FF"));
                tvCoffin.setTextColor(Color.parseColor("#5A55CA"));
                break;
            case "Square":
                btnSquare.setBackgroundColor(Color.parseColor("#F4F3FF"));
                tvSquare.setTextColor(Color.parseColor("#5A55CA"));
                break;
            case "Oval":
                btnOval.setBackgroundColor(Color.parseColor("#F4F3FF"));
                tvOval.setTextColor(Color.parseColor("#5A55CA"));
                break;
            case "Stiletto":
                btnStiletto.setBackgroundColor(Color.parseColor("#F4F3FF"));
                tvStiletto.setTextColor(Color.parseColor("#5A55CA"));
                break;
            case "Round":
                btnRound.setBackgroundColor(Color.parseColor("#F4F3FF"));
                tvRound.setTextColor(Color.parseColor("#5A55CA"));
                break;
        }
    }

    private void changeLengthState(String length) {
        selectedLength = length;

        // Reset Tombol Length jadi putih bersih
        btnShort.setBackgroundColor(Color.parseColor("#FFFFFF"));
        btnShort.setTextColor(Color.parseColor("#1A1C29"));
        btnMedium.setBackgroundColor(Color.parseColor("#FFFFFF"));
        btnMedium.setTextColor(Color.parseColor("#1A1C29"));
        btnLong.setBackgroundColor(Color.parseColor("#FFFFFF"));
        btnLong.setTextColor(Color.parseColor("#1A1C29"));

        // Set tombol aktif jadi ungu
        switch (length) {
            case "Short":
                btnShort.setBackgroundColor(Color.parseColor("#5A55CA"));
                btnShort.setTextColor(Color.WHITE);
                break;
            case "Medium":
                btnMedium.setBackgroundColor(Color.parseColor("#5A55CA"));
                btnMedium.setTextColor(Color.WHITE);
                break;
            case "Long":
                btnLong.setBackgroundColor(Color.parseColor("#5A55CA"));
                btnLong.setTextColor(Color.WHITE);
                break;
        }
    }
}