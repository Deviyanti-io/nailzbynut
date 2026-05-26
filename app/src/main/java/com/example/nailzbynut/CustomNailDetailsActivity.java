package com.example.nailzbynut;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class CustomNailDetailsActivity extends AppCompatActivity {

    private ImageView btnBack;
    private Button btnNext;
    private Button btnSizeS, btnSizeM, btnSizeL;
    private Button btnMinusThumb, btnPlusThumb, btnMinusIndex, btnPlusIndex;
    private Button btnMinusMiddle, btnPlusMiddle, btnMinusRing, btnPlusRing, btnMinusPinky, btnPlusPinky;

    private TextView tvThumb, tvIndex, tvMiddle, tvRing, tvPinky;
    private TextView btnShowGuide;
    private EditText etSpecialNotes;

    private int thumbVal = 14, indexVal = 10, middleVal = 11, ringVal = 10, pinkyVal = 8;
    private String selectedPresetSize = "M";

    private String dataShape, dataLength, dataColorType, dataColorHex, dataFinish;
    private ArrayList<String> dataAddonsList = new ArrayList<>();
    private String dataUploadedImage = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_nail_details);

        // 1. Tangkap data dari CustomNailColorActivity
        Intent incoming = getIntent();
        dataShape = incoming.getStringExtra("SHAPE_DATA") != null ? incoming.getStringExtra("SHAPE_DATA") : "Almond";
        dataLength = incoming.getStringExtra("LENGTH_DATA") != null ? incoming.getStringExtra("LENGTH_DATA") : "Medium";
        dataColorType = incoming.getStringExtra("COLOR_TYPE_DATA") != null ? incoming.getStringExtra("COLOR_TYPE_DATA") : "Solid";
        dataColorHex = incoming.getStringExtra("COLOR_HEX_DATA") != null ? incoming.getStringExtra("COLOR_HEX_DATA") : "#D6001C";
        dataFinish = incoming.getStringExtra("FINISH_DATA") != null ? incoming.getStringExtra("FINISH_DATA") : "Glossy";
        dataUploadedImage = incoming.getStringExtra("UPLOADED_IMAGE_DATA") != null ? incoming.getStringExtra("UPLOADED_IMAGE_DATA") : "";

        if (incoming.getStringArrayListExtra("ADDONS_DATA") != null) {
            dataAddonsList = incoming.getStringArrayListExtra("ADDONS_DATA");
        }

        // 2. Inisialisasi Komponen Sesuai ID XML Anda (Bebas Merah)
        btnSizeS = findViewById(R.id.btn_size_s);
        btnSizeM = findViewById(R.id.btn_size_m);
        btnSizeL = findViewById(R.id.btn_size_l);

        btnMinusThumb = findViewById(R.id.btn_minus_thumb);
        btnPlusThumb = findViewById(R.id.btn_plus_thumb);
        btnMinusIndex = findViewById(R.id.btn_minus_index);
        btnPlusIndex = findViewById(R.id.btn_plus_index);
        btnMinusMiddle = findViewById(R.id.btn_minus_middle);
        btnPlusMiddle = findViewById(R.id.btn_plus_middle);
        btnMinusRing = findViewById(R.id.btn_minus_ring);
        btnPlusRing = findViewById(R.id.btn_plus_ring);
        btnMinusPinky = findViewById(R.id.btn_minus_pinky);
        btnPlusPinky = findViewById(R.id.btn_plus_pinky);

        tvThumb = findViewById(R.id.tv_val_thumb);
        tvIndex = findViewById(R.id.tv_val_index);
        tvMiddle = findViewById(R.id.tv_val_middle);
        tvRing = findViewById(R.id.tv_val_ring);
        tvPinky = findViewById(R.id.tv_val_pinky);

        btnShowGuide = findViewById(R.id.btn_show_guide);
        etSpecialNotes = findViewById(R.id.et_special_notes);
        btnBack = findViewById(R.id.btn_back_details);
        btnNext = findViewById(R.id.btn_next_details);

        if (btnBack != null) btnBack.setOnClickListener(v -> finish());
        if (btnShowGuide != null) btnShowGuide.setOnClickListener(v -> showSizeGuideDialog());

        btnSizeS.setOnClickListener(v -> applyPresetSize("S", 15, 11, 12, 11, 9));
        btnSizeM.setOnClickListener(v -> applyPresetSize("M", 16, 12, 13, 12, 10));
        btnSizeL.setOnClickListener(v -> applyPresetSize("L", 17, 13, 14, 13, 11));

        btnMinusThumb.setOnClickListener(v -> changeValue("thumb", -1));
        btnPlusThumb.setOnClickListener(v -> changeValue("thumb", 1));
        btnMinusIndex.setOnClickListener(v -> changeValue("index", -1));
        btnPlusIndex.setOnClickListener(v -> changeValue("index", 1));
        btnMinusMiddle.setOnClickListener(v -> changeValue("middle", -1));
        btnPlusMiddle.setOnClickListener(v -> changeValue("middle", 1));
        btnMinusRing.setOnClickListener(v -> changeValue("ring", -1));
        btnPlusRing.setOnClickListener(v -> changeValue("ring", 1));
        btnMinusPinky.setOnClickListener(v -> changeValue("pinky", -1));
        btnPlusPinky.setOnClickListener(v -> changeValue("pinky", 1));

        applyPresetSize("M", 16, 12, 13, 12, 10);

        // Kirim data ke ReviewActivity
        btnNext.setOnClickListener(v -> {
            String finalSizeReport = "Preset: " + selectedPresetSize + " (" + thumbVal + "-" + indexVal + "-" + middleVal + "-" + ringVal + "-" + pinkyVal + "mm)";
            String notesText = etSpecialNotes != null ? etSpecialNotes.getText().toString() : "";

            Intent outIntent = new Intent(CustomNailDetailsActivity.this, CustomNailReviewActivity.class);
            outIntent.putExtra("SHAPE_DATA", dataShape);
            outIntent.putExtra("LENGTH_DATA", dataLength);
            outIntent.putExtra("COLOR_TYPE_DATA", dataColorType);
            outIntent.putExtra("COLOR_HEX_DATA", dataColorHex); // Tetap gunakan ini, kita sinkronkan Review-nya di bawah
            outIntent.putExtra("FINISH_DATA", dataFinish);
            outIntent.putExtra("SIZE_REPORT_DATA", finalSizeReport);
            outIntent.putExtra("SPECIAL_NOTES_DATA", notesText);
            outIntent.putExtra("UPLOADED_IMAGE_DATA", dataUploadedImage);
            outIntent.putStringArrayListExtra("ADDONS_DATA", dataAddonsList);

            startActivity(outIntent);
        });
    }

    private void applyPresetSize(String label, int t, int i, int m, int r, int p) {
        selectedPresetSize = label;
        thumbVal = t; indexVal = i; middleVal = m; ringVal = r; pinkyVal = p;
        tvThumb.setText(String.valueOf(thumbVal));
        tvIndex.setText(String.valueOf(indexVal));
        tvMiddle.setText(String.valueOf(middleVal));
        tvRing.setText(String.valueOf(ringVal));
        tvPinky.setText(String.valueOf(pinkyVal));
        resetPresetButtons();
        if (label.equals("S")) highlightPresetButton(btnSizeS);
        else if (label.equals("M")) highlightPresetButton(btnSizeM);
        else if (label.equals("L")) highlightPresetButton(btnSizeL);
    }

    private void changeValue(String finger, int diff) {
        selectedPresetSize = "Custom";
        resetPresetButtons();
        switch (finger) {
            case "thumb": thumbVal = Math.max(5, thumbVal + diff); tvThumb.setText(String.valueOf(thumbVal)); break;
            case "index": indexVal = Math.max(5, indexVal + diff); tvIndex.setText(String.valueOf(indexVal)); break;
            case "middle": middleVal = Math.max(5, middleVal + diff); tvMiddle.setText(String.valueOf(middleVal)); break;
            case "ring": ringVal = Math.max(5, ringVal + diff); tvRing.setText(String.valueOf(ringVal)); break;
            case "pinky": pinkyVal = Math.max(5, pinkyVal + diff); tvPinky.setText(String.valueOf(pinkyVal)); break;
        }
    }

    private void resetPresetButtons() {
        setPureRoundedStyle(btnSizeS, "#FFFFFF", "#222222", false);
        setPureRoundedStyle(btnSizeM, "#FFFFFF", "#222222", false);
        setPureRoundedStyle(btnSizeL, "#FFFFFF", "#222222", false);
    }

    private void highlightPresetButton(Button btn) {
        setPureRoundedStyle(btn, "#7A75F0", "#FFFFFF", true);
    }

    private void setPureRoundedStyle(Button button, String backgroundColorHex, String textColorHex, boolean isActive) {
        if (button == null) return;
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadius(24f);
        shape.setColor(Color.parseColor(backgroundColorHex));
        if (!isActive) {
            shape.setStroke(2, Color.parseColor("#E0E0E0"));
        }
        button.setBackground(shape);
        button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(backgroundColorHex)));
        button.setTextColor(Color.parseColor(textColorHex));
    }

    private void showSizeGuideDialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.img_measure_guide);
        imageView.setAdjustViewBounds(true);
        dialog.setContentView(imageView);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        imageView.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }
}