package com.example.nailzbynut;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ExploreActivity extends AppCompatActivity {

    private ImageView imgN1, imgN2, imgN3, imgN4, imgN5, imgN10, imgN11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        imgN1 = findViewById(R.id.img_n1);
        imgN2 = findViewById(R.id.img_n2);
        imgN3 = findViewById(R.id.img_n3);
        imgN4 = findViewById(R.id.img_n4);
        imgN5 = findViewById(R.id.img_n5);
        imgN10 = findViewById(R.id.img_n10);
        imgN11 = findViewById(R.id.img_n11);

        setupClickAction(imgN1, "Dreamy Lavender", "Rp 95.000");
        setupClickAction(imgN2, "Bluey Pink", "Rp 85.000");
        setupClickAction(imgN3, "Pink Aurora", "Rp 85.000");
        setupClickAction(imgN4, "Red Glossy", "Rp 80.000");
        setupClickAction(imgN5, "Aurora Pearl", "Rp 85.000");
        setupClickAction(imgN10, "Sweet Lavender", "Rp 95.000");
        setupClickAction(imgN11, "Milky White", "Rp 75.000");
    }

    private void setupClickAction(View view, final String nailName, final String nailPrice) {
        if (view != null) {
            view.setOnClickListener(v -> {
                Toast.makeText(ExploreActivity.this, "Opening " + nailName, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ExploreActivity.this, CustomNailShapeActivity.class);
                intent.putExtra("SELECTED_NAIL_NAME", nailName);
                intent.putExtra("SELECTED_NAIL_PRICE", nailPrice);
                startActivity(intent);
            });
        }
    }
}