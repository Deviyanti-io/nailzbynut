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
    private ArrayList<String> dataAddons;
    private String dataUploadedImage = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_nail_details);

        Intent incoming = getIntent();
        dataShape = incoming.getStringExtra("SHAPE_DATA") != null ? incoming.getStringExtra("SHAPE_DATA") : "Almond";
        dataLength = incoming.getStringExtra("LENGTH_DATA") != null ? incoming.getStringExtra("LENGTH_DATA") : "Medium";
        dataColorType = incoming.getStringExtra("COLOR_TYPE_DATA") != null ? incoming.getStringExtra("COLOR_TYPE_DATA") : "Solid";
        dataColorHex = incoming.getStringExtra("COLOR_HEX_DATA") != null ? incoming.getStringExtra("COLOR_HEX_DATA") : "#D6001C";
        dataFinish = incoming.getStringExtra("FINISH_DATA") != null ? incoming.getStringExtra("FINISH_DATA") : "Glossy";
        dataAddons = incoming.getStringArrayListExtra("ADDONS_DATA");
        dataUploadedImage = incoming.getStringExtra("UPLOADED_IMAGE_DATA") != null ? incoming.getStringExtra("UPLOADED_IMAGE_DATA") : "";

        btnSizeS = findViewById(R.id.btn_size_s);
        btnSizeM = findViewById(R.id.btn_size_m);
        btnSizeL = findViewById(R.id.btn_size_l);

        tvThumb = findViewById(R.id.tv_val_thumb);
        btnMinusThumb = findViewById(R.id.btn_minus_thumb);
        btnPlusThumb = findViewById(R.id.btn_plus_thumb);

        tvIndex = findViewById(R.id.tv_val_index);
        btnMinusIndex = findViewById(R.id.btn_minus_index);
        btnPlusIndex = findViewById(R.id.btn_plus_index);

        tvMiddle = findViewById(R.id.tv_val_middle);
        btnMinusMiddle = findViewById(R.id.btn_minus_middle);
        btnPlusMiddle = findViewById(R.id.btn_plus_middle);

        tvRing = findViewById(R.id.tv_val_ring);
        btnMinusRing = findViewById(R.id.btn_minus_ring);
        btnPlusRing = findViewById(R.id.btn_plus_ring);

        tvPinky = findViewById(R.id.tv_val_pinky);
        btnMinusPinky = findViewById(R.id.btn_minus_pinky);
        btnPlusPinky = findViewById(R.id.btn_plus_pinky);

        btnShowGuide = findViewById(R.id.btn_show_guide);
        etSpecialNotes = findViewById(R.id.et_special_notes);
        btnBack = findViewById(R.id.btn_back_details);
        btnNext = findViewById(R.id.btn_next_details);

        if (btnBack != null) btnBack.setOnClickListener(v -> finish());
        if (btnShowGuide != null) btnShowGuide.setOnClickListener(v -> showSizingGuidePopup());

        if (btnSizeS != null) btnSizeS.setOnClickListener(v -> applyPresetSize("S", 15, 11, 12, 11, 9));
        if (btnSizeM != null) btnSizeM.setOnClickListener(v -> applyPresetSize("M", 16, 12, 13, 12, 10));
        if (btnSizeL != null) btnSizeL.setOnClickListener(v -> applyPresetSize("L", 17, 13, 14, 13, 11));

        if (btnMinusThumb != null) btnMinusThumb.setOnClickListener(v -> changeValue("thumb", -1));
        if (btnPlusThumb != null) btnPlusThumb.setOnClickListener(v -> changeValue("thumb", 1));

        if (btnMinusIndex != null) btnMinusIndex.setOnClickListener(v -> changeValue("index", -1));
        if (btnPlusIndex != null) btnPlusIndex.setOnClickListener(v -> changeValue("index", 1));

        if (btnMinusMiddle != null) btnMinusMiddle.setOnClickListener(v -> changeValue("middle", -1));
        if (btnPlusMiddle != null) btnPlusMiddle.setOnClickListener(v -> changeValue("middle", 1));

        if (btnMinusRing != null) btnMinusRing.setOnClickListener(v -> changeValue("ring", -1));
        if (btnPlusRing != null) btnPlusRing.setOnClickListener(v -> changeValue("ring", 1));

        if (btnMinusPinky != null) btnMinusPinky.setOnClickListener(v -> changeValue("pinky", -1));
        if (btnPlusPinky != null) btnPlusPinky.setOnClickListener(v -> changeValue("pinky", 1));

        // Amankan kelengkungan kontainer per jari biar bulat estetik
        LinearLayout cardContainer = findViewById(R.id.et_special_notes) != null ? (LinearLayout) findViewById(R.id.tv_val_thumb).getParent().getParent() : null;
        if (cardContainer != null) {
            GradientDrawable containerShape = new GradientDrawable();
            containerShape.setShape(GradientDrawable.RECTANGLE);
            containerShape.setCornerRadius(32f);
            containerShape.setColor(Color.WHITE);
            cardContainer.setBackground(containerShape);
        }

        // Jalankan keadaan awal default ukuran M agar menyala bulat premium
        applyPresetSize("M", 16, 12, 13, 12, 10);

        if (btnNext != null) {
            btnNext.setOnClickListener(v -> {
                String finalSizeReport = "Preset: " + selectedPresetSize + " (" + thumbVal + "-" + indexVal + "-" + middleVal + "-" + ringVal + "-" + pinkyVal + "mm)";
                String notesText = etSpecialNotes != null ? etSpecialNotes.getText().toString() : "";

                Intent outIntent = new Intent(CustomNailDetailsActivity.this, CustomNailReviewActivity.class);
                outIntent.putExtra("SHAPE_DATA", dataShape);
                outIntent.putExtra("LENGTH_DATA", dataLength);
                outIntent.putExtra("COLOR_TYPE_DATA", dataColorType);
                outIntent.putExtra("COLOR_HEX_DATA", dataColorHex);
                outIntent.putExtra("FINISH_DATA", dataFinish);
                outIntent.putStringArrayListExtra("ADDONS_DATA", dataAddons);
                outIntent.putExtra("SIZE_REPORT_DATA", finalSizeReport);
                outIntent.putExtra("SPECIAL_NOTES_DATA", notesText);
                outIntent.putExtra("UPLOADED_IMAGE_DATA", dataUploadedImage);
                startActivity(outIntent);
            });
        }
    }

    private void showSizingGuidePopup() {
        Dialog guideDialog = new Dialog(this);
        guideDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.img_measure_guide);
        imageView.setAdjustViewBounds(true);
        guideDialog.setContentView(imageView);
        if (guideDialog.getWindow() != null) {
            guideDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        imageView.setOnClickListener(v -> guideDialog.dismiss());
        guideDialog.show();
    }

    private void applyPresetSize(String label, int t, int i, int m, int r, int p) {
        selectedPresetSize = label;
        thumbVal = t; indexVal = i; middleVal = m; ringVal = r; pinkyVal = p;

        if (tvThumb != null) tvThumb.setText(String.valueOf(thumbVal));
        if (tvIndex != null) tvIndex.setText(String.valueOf(indexVal));
        if (tvMiddle != null) tvMiddle.setText(String.valueOf(middleVal));
        if (tvRing != null) tvRing.setText(String.valueOf(ringVal));
        if (tvPinky != null) tvPinky.setText(String.valueOf(pinkyVal));

        resetPresetButtons();
        if (label.equals("S")) highlightPresetButton(btnSizeS);
        else if (label.equals("M")) highlightPresetButton(btnSizeM);
        else if (label.equals("L")) highlightPresetButton(btnSizeL);
    }

    private void changeValue(String finger, int diff) {
        selectedPresetSize = "Custom";
        resetPresetButtons();

        switch (finger) {
            case "thumb": thumbVal = Math.max(5, thumbVal + diff); if (tvThumb != null) tvThumb.setText(String.valueOf(thumbVal)); break;
            case "index": indexVal = Math.max(5, indexVal + diff); if (tvIndex != null) tvIndex.setText(String.valueOf(indexVal)); break;
            case "middle": middleVal = Math.max(5, middleVal + diff); if (tvMiddle != null) tvMiddle.setText(String.valueOf(middleVal)); break;
            case "ring": ringVal = Math.max(5, ringVal + diff); if (tvRing != null) tvRing.setText(String.valueOf(ringVal)); break;
            case "pinky": pinkyVal = Math.max(5, pinkyVal + diff); if (tvPinky != null) tvPinky.setText(String.valueOf(pinkyVal)); break;
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
}