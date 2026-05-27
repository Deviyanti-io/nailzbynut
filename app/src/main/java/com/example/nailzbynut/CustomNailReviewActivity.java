package com.example.nailzbynut;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import java.util.ArrayList;

public class CustomNailReviewActivity extends AppCompatActivity {

    private ImageView btnBack;
    private AppCompatButton btnToBooking;
    private View viewColorPreview;

    private TextView tvShape, tvLength, tvColor, tvFinish, tvAddons, tvSize, tvNotes;

    private String shape, length, colorType, colorHex, finish, sizeReport, notes, uploadedImage;
    private ArrayList<String> addonsList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_nail_review);

        Intent incoming = getIntent();
        shape = incoming.getStringExtra("SHAPE_DATA") != null ? incoming.getStringExtra("SHAPE_DATA") : "Almond";
        length = incoming.getStringExtra("LENGTH_DATA") != null ? incoming.getStringExtra("LENGTH_DATA") : "Medium";
        colorType = incoming.getStringExtra("COLOR_TYPE_DATA") != null ? incoming.getStringExtra("COLOR_TYPE_DATA") : "Solid";
        colorHex = incoming.getStringExtra("COLOR_HEX_DATA") != null ? incoming.getStringExtra("COLOR_HEX_DATA") : "#D6001C";
        finish = incoming.getStringExtra("FINISH_DATA") != null ? incoming.getStringExtra("FINISH_DATA") : "Glossy";
        addonsList = incoming.getStringArrayListExtra("ADDONS_DATA");
        sizeReport = incoming.getStringExtra("SIZE_REPORT_DATA") != null ? incoming.getStringExtra("SIZE_REPORT_DATA") : "M";
        notes = incoming.getStringExtra("SPECIAL_NOTES_DATA") != null ? incoming.getStringExtra("SPECIAL_NOTES_DATA") : "";
        uploadedImage = incoming.getStringExtra("UPLOADED_IMAGE_DATA") != null ? incoming.getStringExtra("UPLOADED_IMAGE_DATA") : "";

        btnBack = findViewById(R.id.btn_back_review);
        btnToBooking = findViewById(R.id.btn_to_booking);
        viewColorPreview = findViewById(R.id.view_review_color_preview);

        tvShape = findViewById(R.id.tv_review_shape);
        tvLength = findViewById(R.id.tv_review_length);
        tvColor = findViewById(R.id.tv_review_color);
        tvFinish = findViewById(R.id.tv_review_finish);
        tvAddons = findViewById(R.id.tv_review_addons);
        tvSize = findViewById(R.id.tv_review_size);
        tvNotes = findViewById(R.id.tv_review_notes);

        if (btnBack != null) btnBack.setOnClickListener(v -> finish());

        if (tvShape != null) tvShape.setText(shape);
        if (tvLength != null) tvLength.setText(length);
        if (tvFinish != null) tvFinish.setText(finish);
        if (tvSize != null) tvSize.setText(sizeReport);

        if (tvNotes != null) {
            tvNotes.setText(notes.isEmpty() ? "Tidak ada catatan tambahan." : notes);
        }

        if (tvColor != null) {
            tvColor.setText(colorType + " (" + colorHex + ")");
        }

        if (viewColorPreview != null) {
            try {
                viewColorPreview.setBackgroundColor(Color.parseColor(colorHex));
            } catch (Exception e) {
                viewColorPreview.setBackgroundColor(Color.parseColor("#7A75F0"));
            }
        }

        if (tvAddons != null) {
            if (addonsList != null && !addonsList.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < addonsList.size(); i++) {
                    sb.append(addonsList.get(i));
                    if (i < addonsList.size() - 1) sb.append(", ");
                }
                tvAddons.setText(sb.toString());
            } else {
                tvAddons.setText("Tanpa aksesoris tambahan");
            }
        }

        LinearLayout reviewCard = findViewById(R.id.layout_review_card);
        if (reviewCard != null) {
            GradientDrawable cardShape = new GradientDrawable();
            cardShape.setShape(GradientDrawable.RECTANGLE);
            cardShape.setCornerRadius(32f);
            cardShape.setColor(Color.WHITE);
            reviewCard.setBackground(cardShape);
        }

        if (!uploadedImage.isEmpty()) {
            TextView tvImageNote = findViewById(R.id.tv_image_note);
            if (tvImageNote == null) {
                tvImageNote = new TextView(this);
                LinearLayout parent = findViewById(R.id.layout_review_card);
                if (parent != null) {
                    tvImageNote.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    ));
                    tvImageNote.setPadding(0, 16, 0, 0);
                    tvImageNote.setText("📷 Foto referensi tersedia");
                    tvImageNote.setTextColor(Color.parseColor("#7A75F0"));
                    parent.addView(tvImageNote);
                }
            } else {
                tvImageNote.setText("📷 Foto referensi tersedia");
                tvImageNote.setVisibility(View.VISIBLE);
            }
        }

        // PERBAIKAN UTAMA: Mengalirkan data asli (BUKAN hardcoded hijau lagi)
        if (btnToBooking != null) {
            btnToBooking.setOnClickListener(v -> {
                Intent intent = new Intent(CustomNailReviewActivity.this, BookingAppointmentActivity.class);
                intent.putExtra("FINAL_SHAPE", shape);
                intent.putExtra("FINAL_LENGTH", length);
                intent.putExtra("FINAL_COLOR_TYPE", colorType);
                intent.putExtra("FINAL_COLOR", colorHex); // Menyertakan warna pilihan user asli
                intent.putExtra("FINAL_FINISH", finish);
                intent.putExtra("FINAL_SIZE", sizeReport);
                intent.putExtra("FINAL_NOTES", notes);
                intent.putExtra("FINAL_IMAGE", uploadedImage);
                intent.putStringArrayListExtra("FINAL_ADDONS", addonsList);
                startActivity(intent);
            });
        }
    }
}