package com.example.nailzbynut;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class CustomNailShapeActivity extends AppCompatActivity {

    private ImageView btnBack;
    private LinearLayout btnAlmond, btnCoffin, btnSquare, btnOval, btnStiletto, btnRound, btnSquoval, btnLipstick;
    private TextView tvAlmond, tvCoffin, tvSquare, tvOval, tvStiletto, tvRound, tvSquoval, tvLipstick;
    private AppCompatButton btnNext, btnShort, btnMedium, btnLong;

    private String selectedShape = "Almond";
    private String selectedLength = "Medium";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_nail_shape);

        btnBack = findViewById(R.id.btn_back);
        btnAlmond = findViewById(R.id.btn_shape_almond);
        btnCoffin = findViewById(R.id.btn_shape_coffin);
        btnSquare = findViewById(R.id.btn_shape_square);
        btnOval = findViewById(R.id.btn_shape_oval);
        btnStiletto = findViewById(R.id.btn_shape_stiletto);
        btnRound = findViewById(R.id.btn_shape_round);
        btnSquoval = findViewById(R.id.btn_shape_squoval);
        btnLipstick = findViewById(R.id.btn_shape_lipstick);

        tvAlmond = findViewById(R.id.tv_almond);
        tvCoffin = findViewById(R.id.tv_coffin);
        tvSquare = findViewById(R.id.tv_square);
        tvOval = findViewById(R.id.tv_oval);
        tvStiletto = findViewById(R.id.tv_stiletto);
        tvRound = findViewById(R.id.tv_round);
        tvSquoval = findViewById(R.id.tv_squoval);
        tvLipstick = findViewById(R.id.tv_lipstick);

        btnShort = findViewById(R.id.btn_len_short);
        btnMedium = findViewById(R.id.btn_len_medium);
        btnLong = findViewById(R.id.btn_len_long);
        btnNext = findViewById(R.id.btn_next_step);

        if (btnBack != null) btnBack.setOnClickListener(v -> finish());

        changeSelectedState("Almond");
        changeLengthState("Medium");

        btnAlmond.setOnClickListener(v -> changeSelectedState("Almond"));
        btnCoffin.setOnClickListener(v -> changeSelectedState("Coffin"));
        btnSquare.setOnClickListener(v -> changeSelectedState("Square"));
        btnOval.setOnClickListener(v -> changeSelectedState("Oval"));
        btnStiletto.setOnClickListener(v -> changeSelectedState("Stiletto"));
        btnRound.setOnClickListener(v -> changeSelectedState("Round"));
        btnSquoval.setOnClickListener(v -> changeSelectedState("Squoval"));
        btnLipstick.setOnClickListener(v -> changeSelectedState("Lipstick"));

        btnShort.setOnClickListener(v -> changeLengthState("Short"));
        btnMedium.setOnClickListener(v -> changeLengthState("Medium"));
        btnLong.setOnClickListener(v -> changeLengthState("Long"));

        btnNext.setOnClickListener(v -> {
            Intent intent = new Intent(CustomNailShapeActivity.this, CustomNailColorActivity.class);
            intent.putExtra("SHAPE_DATA", selectedShape);
            intent.putExtra("LENGTH_DATA", selectedLength);
            startActivity(intent);
        });
    }

    private void changeSelectedState(String shape) {
        selectedShape = shape;

        // Reset semua tombol ke putih melengkung mewah dengan bayangan tipis
        setPureRoundedStyle(btnAlmond, tvAlmond, "#FFFFFF", "#222222");
        setPureRoundedStyle(btnCoffin, tvCoffin, "#FFFFFF", "#222222");
        setPureRoundedStyle(btnSquare, tvSquare, "#FFFFFF", "#222222");
        setPureRoundedStyle(btnOval, tvOval, "#FFFFFF", "#222222");
        setPureRoundedStyle(btnStiletto, tvStiletto, "#FFFFFF", "#222222");
        setPureRoundedStyle(btnRound, tvRound, "#FFFFFF", "#222222");
        setPureRoundedStyle(btnSquoval, tvSquoval, "#FFFFFF", "#222222");
        setPureRoundedStyle(btnLipstick, tvLipstick, "#FFFFFF", "#222222");

        // Set tombol yang aktif ke warna ungu lavender sesuai referensi baru Anda
        switch (shape) {
            case "Almond": setPureRoundedStyle(btnAlmond, tvAlmond, "#7A75F0", "#FFFFFF"); break;
            case "Coffin": setPureRoundedStyle(btnCoffin, tvCoffin, "#7A75F0", "#FFFFFF"); break;
            case "Square": setPureRoundedStyle(btnSquare, tvSquare, "#7A75F0", "#FFFFFF"); break;
            case "Oval": setPureRoundedStyle(btnOval, tvOval, "#7A75F0", "#FFFFFF"); break;
            case "Stiletto": setPureRoundedStyle(btnStiletto, tvStiletto, "#7A75F0", "#FFFFFF"); break;
            case "Round": setPureRoundedStyle(btnRound, tvRound, "#7A75F0", "#FFFFFF"); break;
            case "Squoval": setPureRoundedStyle(btnSquoval, tvSquoval, "#7A75F0", "#FFFFFF"); break;
            case "Lipstick": setPureRoundedStyle(btnLipstick, tvLipstick, "#7A75F0", "#FFFFFF"); break;
        }
    }

    private void setPureRoundedStyle(LinearLayout layout, TextView textView, String bgColorHex, String textColorHex) {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadius(32f); // Kunci kelengkungan sudut bulat premium
        shape.setColor(Color.parseColor(bgColorHex));
        layout.setBackground(shape);
        textView.setTextColor(Color.parseColor(textColorHex));
    }

    @SuppressLint("RestrictedApi")
    private void changeLengthState(String length) {
        selectedLength = length;
        android.content.res.ColorStateList whiteBg = android.content.res.ColorStateList.valueOf(Color.parseColor("#FFFFFF"));
        int darkText = Color.parseColor("#222222");

        btnShort.setSupportBackgroundTintList(whiteBg); btnShort.setTextColor(darkText);
        btnMedium.setSupportBackgroundTintList(whiteBg); btnMedium.setTextColor(darkText);
        btnLong.setSupportBackgroundTintList(whiteBg); btnLong.setTextColor(darkText);

        android.content.res.ColorStateList purpleBg = android.content.res.ColorStateList.valueOf(Color.parseColor("#7A75F0"));

        switch (length) {
            case "Short": btnShort.setSupportBackgroundTintList(purpleBg); btnShort.setTextColor(Color.WHITE); break;
            case "Medium": btnMedium.setSupportBackgroundTintList(purpleBg); btnMedium.setTextColor(Color.WHITE); break;
            case "Long": btnLong.setSupportBackgroundTintList(purpleBg); btnLong.setTextColor(Color.WHITE); break;
        }
    }
}