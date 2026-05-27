package com.example.nailzbynut;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ExploreActivity extends AppCompatActivity {

    // 1. Deklarasi tombol Heart Pojok Atas
    private ImageView btnHeartTop;

    // 2. Deklarasi 24 Gambar Kuku Utama
    private ImageView imgN1, imgN2, imgN3, imgN4, imgN5, imgN6, imgN7, imgN8, imgN9, imgN10,
            imgN11, imgN12, imgN13, imgN14, imgN15, imgN16, imgN17, imgN18, imgN19, imgN20,
            imgN21, imgN22, imgN23, imgN24;

    // 3. Deklarasi 24 Tombol Heart Kecil (Tambahkan bagian ini)
    private ImageView heartN1, heartN2, heartN3, heartN4, heartN5, heartN6, heartN7, heartN8, heartN9, heartN10,
            heartN11, heartN12, heartN13, heartN14, heartN15, heartN16, heartN17, heartN18, heartN19, heartN20,
            heartN21, heartN22, heartN23, heartN24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        // --- INISIALISASI TOMBOL HEART POJOK ATAS ---
        btnHeartTop = findViewById(R.id.btn_heart_top);
        if (btnHeartTop != null) {
            btnHeartTop.setOnClickListener(v -> {
                Intent intent = new Intent(ExploreActivity.this, WishlistActivity.class);
                startActivity(intent);
            });
        }

        // --- INISIALISASI 24 GAMBAR KUKU DARI XML ---
        imgN1 = findViewById(R.id.img_n1);
        imgN2 = findViewById(R.id.img_n2);
        imgN3 = findViewById(R.id.img_n3);
        imgN4 = findViewById(R.id.img_n4);
        imgN5 = findViewById(R.id.img_n5);
        imgN6 = findViewById(R.id.img_n6);
        imgN7 = findViewById(R.id.img_n7);
        imgN8 = findViewById(R.id.img_n8);
        imgN9 = findViewById(R.id.img_n9);
        imgN10 = findViewById(R.id.img_n10);
        imgN11 = findViewById(R.id.img_n11);
        imgN12 = findViewById(R.id.img_n12);
        imgN13 = findViewById(R.id.img_n13);
        imgN14 = findViewById(R.id.img_n14);
        imgN15 = findViewById(R.id.img_n15);
        imgN16 = findViewById(R.id.img_n16);
        imgN17 = findViewById(R.id.img_n17);
        imgN18 = findViewById(R.id.img_n18);
        imgN19 = findViewById(R.id.img_n19);
        imgN20 = findViewById(R.id.img_n20);
        imgN21 = findViewById(R.id.img_n21);
        imgN22 = findViewById(R.id.img_n22);
        imgN23 = findViewById(R.id.img_n23);
        imgN24 = findViewById(R.id.img_n24);

        // --- INISIALISASI 24 TOMBOL HEART KECIL (Tambahkan bagian ini) ---
        heartN1 = findViewById(R.id.heart_n1);
        heartN2 = findViewById(R.id.heart_n2);
        heartN3 = findViewById(R.id.heart_n3);
        heartN4 = findViewById(R.id.heart_n4);
        heartN5 = findViewById(R.id.heart_n5);
        heartN6 = findViewById(R.id.heart_n6);
        heartN7 = findViewById(R.id.heart_n7);
        heartN8 = findViewById(R.id.heart_n8);
        heartN9 = findViewById(R.id.heart_n9);
        heartN10 = findViewById(R.id.heart_n10);
        heartN11 = findViewById(R.id.heart_n11);
        heartN12 = findViewById(R.id.heart_n12);
        heartN13 = findViewById(R.id.heart_n13);
        heartN14 = findViewById(R.id.heart_n14);
        heartN15 = findViewById(R.id.heart_n15);
        heartN16 = findViewById(R.id.heart_n16);
        heartN17 = findViewById(R.id.heart_n17);
        heartN18 = findViewById(R.id.heart_n18);
        heartN19 = findViewById(R.id.heart_n19);
        heartN20 = findViewById(R.id.heart_n20);
        heartN21 = findViewById(R.id.heart_n21);
        heartN22 = findViewById(R.id.heart_n22);
        heartN23 = findViewById(R.id.heart_n23);
        heartN24 = findViewById(R.id.heart_n24);

        // --- SETUP AKSI KLIK TOMBOL HEART (Tambahkan bagian ini agar tombol aktif) ---
        setupHeartAction(heartN1, "Midnight Blue");
        setupHeartAction(heartN2, "Blue Petal");
        setupHeartAction(heartN3, "Pink Aurora");
        setupHeartAction(heartN4, "Red Scarlet");
        setupHeartAction(heartN5, "Pink Glaze");
        setupHeartAction(heartN6, "Sweet Pink");
        setupHeartAction(heartN7, "Simple White Blue");
        setupHeartAction(heartN8, "Pink Rose");
        setupHeartAction(heartN9, "Blush Petal");
        setupHeartAction(heartN10, "Mocha Cream");
        setupHeartAction(heartN11, "Red Cherry");
        setupHeartAction(heartN12, "Midnight Blue");
        setupHeartAction(heartN13, "Blue Haze");
        setupHeartAction(heartN14, "Royale Brown");
        setupHeartAction(heartN15, "Silver Mist");
        setupHeartAction(heartN16, "Red Bow");
        setupHeartAction(heartN17, "Golden Muse");
        setupHeartAction(heartN18, "Frosted Blue");
        setupHeartAction(heartN19, "Red Aurora");
        setupHeartAction(heartN20, "Pink Mirage");
        setupHeartAction(heartN21, "Milky Red");
        setupHeartAction(heartN22, "Nude Luxe");
        setupHeartAction(heartN23, "Peach Aura");
        setupHeartAction(heartN24, "Flower Chrome");

        // --- SETUP AKSI KLIK SESUAI NAMA & HARGA DI XML ---
        setupClickAction(imgN1, "Midnight Blue", "Rp 90.000");
        setupClickAction(imgN2, "Blue Petal", "Rp 90.000");
        setupClickAction(imgN3, "Pink Aurora", "Rp 95.000");
        setupClickAction(imgN4, "Red Scarlet", "Rp 100.000");
        setupClickAction(imgN5, "Pink Glaze", "Rp 85.000");
        setupClickAction(imgN6, "Sweet Pink", "Rp 80.000");
        setupClickAction(imgN7, "Simple White Blue", "Rp 80.000");
        setupClickAction(imgN8, "Pink Rose", "Rp 100.000");
        setupClickAction(imgN9, "Blush Petal", "Rp 95.000");
        setupClickAction(imgN10, "Mocha Cream", "Rp 95.000");
        setupClickAction(imgN11, "Red Cherry", "Rp 90.000");
        setupClickAction(imgN12, "Midnight Blue", "Rp 90.000");
        setupClickAction(imgN13, "Blue Haze", "Rp 95.000");
        setupClickAction(imgN14, "Royale Brown", "Rp 90.000");
        setupClickAction(imgN15, "Silver Mist", "Rp 85.000");
        setupClickAction(imgN16, "Red Bow", "Rp 80.000");
        setupClickAction(imgN17, "Golden Muse", "Rp 80.000");
        setupClickAction(imgN18, "Frosted Blue", "Rp 80.000");
        setupClickAction(imgN19, "Red Aurora", "Rp 90.000");
        setupClickAction(imgN20, "Pink Mirage", "Rp 90.000");
        setupClickAction(imgN21, "Milky Red", "Rp 85.000");
        setupClickAction(imgN22, "Nude Luxe", "Rp 85.000");
        setupClickAction(imgN23, "Peach Aura", "Rp 90.000");
        setupClickAction(imgN24, "Flower Chrome", "Rp 95.000");
    }

    private void setupHeartAction(ImageView heartView, final String nailName) {
        if (heartView != null) {
            heartView.setTag(false); // Default: false (belum disukai)

            heartView.setOnClickListener(v -> {
                boolean isLiked = (boolean) heartView.getTag();
                if (!isLiked) {
                    // Berganti ke ikon hati penuh saat disukai
                    heartView.setImageResource(R.drawable.ic_heart_on);
                    heartView.setTag(true);
                    Toast.makeText(ExploreActivity.this, nailName + " ditambahkan ke Wishlist", Toast.LENGTH_SHORT).show();
                } else {
                    // Kembali ke ikon hati kosong saat batal disukai
                    heartView.setImageResource(R.drawable.ic_heart_off);
                    heartView.setTag(false);
                    Toast.makeText(ExploreActivity.this, nailName + " dihapus dari Wishlist", Toast.LENGTH_SHORT).show();
                }
            });
        }
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