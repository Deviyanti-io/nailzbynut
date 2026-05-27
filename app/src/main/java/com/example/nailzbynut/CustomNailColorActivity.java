package com.example.nailzbynut;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class CustomNailColorActivity extends AppCompatActivity {

    private ImageView btnBack;
    private Button btnNext;
    private Button btnTypeSolid, btnTypeGradient, btnTypeFrench, btnTypeCatEye;
    private LinearLayout btnGlossy, btnMatte, btnChrome, btnGlitter;
    private LinearLayout btnUploadPhoto;
    private GridLayout colorGrid;

    private String selectedColorType = "Solid";
    private String selectedColorHex = "#D6001C";
    private String selectedFinish = "Glossy";
    private ArrayList<String> selectedAddonsList = new ArrayList<>();
    private String dataShape = "Almond", dataLength = "Medium";

    private String selectedImageUriString = "";
    private ActivityResultLauncher<Intent> galleryLauncher;

    // Kumpulan Data Palet Warna Berwarna Indah Beserta Namanya
    private final ArrayList<NailColor> solidList = new ArrayList<>();
    private final ArrayList<NailColor> gradientList = new ArrayList<>();
    private final ArrayList<NailColor> frenchList = new ArrayList<>();
    private final ArrayList<NailColor> catEyeList = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_nail_color);

        // Register launcher di awal Lifecycle agar aman
        galleryLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        Uri imageUri = result.getData().getData();
                        if (imageUri != null) {
                            selectedImageUriString = imageUri.toString();
                            Toast.makeText(this, "Foto referensi berhasil dimuat!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        // Ambil data estafet dari halaman sebelumnya
        if (getIntent().getStringExtra("SHAPE_DATA") != null) {
            dataShape = getIntent().getStringExtra("SHAPE_DATA");
        }
        if (getIntent().getStringExtra("LENGTH_DATA") != null) {
            dataLength = getIntent().getStringExtra("LENGTH_DATA");
        }

        // Inisialisasi komponen UI
        btnTypeSolid = findViewById(R.id.btn_type_solid);
        btnTypeGradient = findViewById(R.id.btn_type_gradient);
        btnTypeFrench = findViewById(R.id.btn_type_french);
        btnTypeCatEye = findViewById(R.id.btn_type_cateye);

        btnGlossy = findViewById(R.id.btn_finish_glossy);
        btnMatte = findViewById(R.id.btn_finish_matte);
        btnChrome = findViewById(R.id.btn_finish_chrome);
        btnGlitter = findViewById(R.id.btn_finish_glitter);
        btnUploadPhoto = findViewById(R.id.btn_upload_photo_container);

        btnBack = findViewById(R.id.btn_back_color);
        btnNext = findViewById(R.id.btn_next_color);
        colorGrid = findViewById(R.id.color_grid);

        // Isi dan strukturkan data warna
        populateColorData();

        btnBack.setOnClickListener(v -> finish());

        // Set state default awal halaman
        updateColorTypeState("Solid");
        updateFinishState("Glossy");

        // Click listener tipe finish coat
        btnGlossy.setOnClickListener(v -> updateFinishState("Glossy"));
        btnMatte.setOnClickListener(v -> updateFinishState("Matte"));
        btnChrome.setOnClickListener(v -> updateFinishState("Chrome"));
        btnGlitter.setOnClickListener(v -> updateFinishState("Glitter"));

        // Setup aksesoris tambahan (Add-ons) kembali diaktifkan lengkap
        setupAddonToggle(findViewById(R.id.addon_charms), "Charms");
        setupAddonToggle(findViewById(R.id.addon_pearls), "Pearls");
        setupAddonToggle(findViewById(R.id.addon_rhinestone), "Rhinestone");
        setupAddonToggle(findViewById(R.id.addon_flower), "3D Flower");
        setupAddonToggle(findViewById(R.id.addon_stickers), "Stickers");
        setupAddonToggle(findViewById(R.id.addon_bow), "Bow");

        if (btnUploadPhoto != null) {
            GradientDrawable uploadShape = new GradientDrawable();
            uploadShape.setShape(GradientDrawable.RECTANGLE);
            uploadShape.setCornerRadius(32f);
            uploadShape.setColor(Color.WHITE);
            uploadShape.setStroke(2, Color.parseColor("#E0E0E0"));
            btnUploadPhoto.setBackground(uploadShape);

            btnUploadPhoto.setOnClickListener(v -> {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                galleryLauncher.launch(galleryIntent);
            });
        }

        // Setup click listener kategori tipe kuku
        btnTypeSolid.setOnClickListener(v -> updateColorTypeState("Solid"));
        btnTypeGradient.setOnClickListener(v -> updateColorTypeState("Ombre"));
        btnTypeFrench.setOnClickListener(v -> updateColorTypeState("French Tip"));
        btnTypeCatEye.setOnClickListener(v -> updateColorTypeState("Cat Eye"));

        // Tombol next mengirimkan estafet data secara aman tanpa kehilangan data shape/length
        btnNext.setOnClickListener(v -> {
            Intent intent = new Intent(CustomNailColorActivity.this, CustomNailDetailsActivity.class);
            intent.putExtra("SHAPE_DATA", dataShape);
            intent.putExtra("LENGTH_DATA", dataLength);
            intent.putExtra("COLOR_TYPE_DATA", selectedColorType);
            intent.putExtra("COLOR_HEX_DATA", selectedColorHex);
            intent.putExtra("FINISH_DATA", selectedFinish);
            intent.putStringArrayListExtra("ADDONS_DATA", selectedAddonsList);
            intent.putExtra("UPLOADED_IMAGE_DATA", selectedImageUriString);
            startActivity(intent);
        });
    }

    private void populateColorData() {
        // 1. Solid Colors
        solidList.add(new NailColor("Black", "#000000"));
        solidList.add(new NailColor("Milk White", "#F2F0EB"));
        solidList.add(new NailColor("Cherry Red", "#D6001C"));
        solidList.add(new NailColor("Silver", "#C4C2C0"));
        solidList.add(new NailColor("Caramel", "#D9A05B"));
        solidList.add(new NailColor("Peach Nude", "#F5D6C4"));
        solidList.add(new NailColor("Soft Peach", "#F5D1BC"));
        solidList.add(new NailColor("Rose Pink", "#E8A7B5"));
        solidList.add(new NailColor("Coral", "#F7A399"));
        solidList.add(new NailColor("Dusty Pink", "#E394A4"));
        solidList.add(new NailColor("Mauve", "#BA7EA9"));
        solidList.add(new NailColor("Dark Purple", "#4E2B6B"));
        solidList.add(new NailColor("Wine Red", "#731822"));
        solidList.add(new NailColor("Hot Pink", "#E04D79"));
        solidList.add(new NailColor("Lavender", "#8F63A8"));
        solidList.add(new NailColor("Royal Purple", "#78388C"));
        solidList.add(new NailColor("Pastel Lavender", "#DED2F9"));
        solidList.add(new NailColor("Soft Lilac", "#E8C5E5"));
        solidList.add(new NailColor("Taupe", "#8F8073"));
        solidList.add(new NailColor("Nude Beige", "#D1BCB2"));
        solidList.add(new NailColor("Ocean Blue", "#008EA6"));
        solidList.add(new NailColor("Navy Blue", "#0B4273"));
        solidList.add(new NailColor("Lime Green", "#9ED463"));
        solidList.add(new NailColor("Brown", "#8B5A2B"));
        solidList.add(new NailColor("Teal", "#118199"));
        solidList.add(new NailColor("Deep Teal", "#0F6E85"));
        solidList.add(new NailColor("Dark Cyan", "#08576B"));
        solidList.add(new NailColor("Denim Blue", "#5785A6"));
        solidList.add(new NailColor("Midnight Blue", "#1C364A"));

        // 2. Ombre / Gradient Colors
        gradientList.add(new NailColor("Blush Pink", "#FF9A9E"));
        gradientList.add(new NailColor("Orchid", "#FECFEF"));
        gradientList.add(new NailColor("Sky Blue", "#A1C4FD"));
        gradientList.add(new NailColor("Ice Gradient", "#C2E9FB"));
        gradientList.add(new NailColor("Sunset Gel", "#F6D365"));
        gradientList.add(new NailColor("Melon Candy", "#FDA085"));
        gradientList.add(new NailColor("Lilac Dream", "#E0C3FC"));
        gradientList.add(new NailColor("Soft Ocean", "#8EC5FC"));
        gradientList.add(new NailColor("Mint Fresh", "#84FAB0"));
        gradientList.add(new NailColor("Aqua Bloom", "#8FD3F4"));

        // 3. French Tip Colors
        frenchList.add(new NailColor("Classic White", "#FFFFFF"));
        frenchList.add(new NailColor("Rose Petal", "#FFE4E1"));
        frenchList.add(new NailColor("Beige Silk", "#F5F5DC"));
        frenchList.add(new NailColor("Gold Line", "#FFD700"));
        frenchList.add(new NailColor("Deep Onyx", "#000000"));
        frenchList.add(new NailColor("Soft Violet", "#E6E6FA"));
        frenchList.add(new NailColor("Powder Blue", "#B0E0E6"));
        frenchList.add(new NailColor("Blossom", "#FFB6C1"));

        // 4. Cat Eye Colors
        catEyeList.add(new NailColor("Galaxy Velvet", "#2E1A47"));
        catEyeList.add(new NailColor("Deep Cosmic", "#1A2E40"));
        catEyeList.add(new NailColor("Jade Magnetic", "#1A402E"));
        catEyeList.add(new NailColor("Amber Flare", "#403A1A"));
        catEyeList.add(new NailColor("Ruby Laser", "#401A1A"));
        catEyeList.add(new NailColor("Nebula Purple", "#331A47"));
    }

    private void generateColorPalette(ArrayList<NailColor> colors) {
        colorGrid.removeAllViews();

        for (NailColor color : colors) {
            LinearLayout item = new LinearLayout(this);
            item.setOrientation(LinearLayout.VERTICAL);
            item.setGravity(Gravity.CENTER);

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.setMargins(16, 16, 16, 16);
            item.setLayoutParams(params);

            View circle = new View(this);
            // Mengatur ukuran lingkaran warna palet biar proporsional (90px x 90px)
            LinearLayout.LayoutParams circleParams = new LinearLayout.LayoutParams(90, 90);
            circle.setLayoutParams(circleParams);

            GradientDrawable shape = new GradientDrawable();
            shape.setShape(GradientDrawable.OVAL);
            shape.setColor(Color.parseColor(color.getHex()));

            // Beri border abu-abu tipis jika warnanya putih/sangat terang agar terlihat
            if (color.getHex().equalsIgnoreCase("#FFFFFF") || color.getHex().equalsIgnoreCase("#F2F0EB")) {
                shape.setStroke(2, Color.parseColor("#DDDDDD"));
            }
            circle.setBackground(shape);

            TextView tvName = new TextView(this);
            tvName.setText(color.getName());
            tvName.setTextSize(10);
            tvName.setTextColor(Color.parseColor("#222222"));
            tvName.setGravity(Gravity.CENTER);
            tvName.setPadding(0, 8, 0, 0);

            item.addView(circle);
            item.addView(tvName);

            item.setOnClickListener(v -> {
                selectedColorHex = color.getHex();
                Toast.makeText(this, "Warna dipilih: " + color.getName(), Toast.LENGTH_SHORT).show();
            });

            colorGrid.addView(item);
        }
    }

    private void updateColorTypeState(String type) {
        selectedColorType = type;
        Button[] tabs = {btnTypeSolid, btnTypeGradient, btnTypeFrench, btnTypeCatEye};

        for (Button b : tabs) {
            if (b != null) setTabButtonStyle(b, "#FFFFFF", "#222222", false);
        }

        Button active = btnTypeSolid;
        if (type.equals("Ombre") && btnTypeGradient != null) active = btnTypeGradient;
        else if (type.equals("French Tip") && btnTypeFrench != null) active = btnTypeFrench;
        else if (type.equals("Cat Eye") && btnTypeCatEye != null) active = btnTypeCatEye;

        if (active != null) {
            setTabButtonStyle(active, "#7A75F0", "#FFFFFF", true);
        }

        // Render palet berdasarkan tab kategori aktif secara dinamis
        ArrayList<NailColor> targetList = type.equals("Solid") ? solidList :
                type.equals("Ombre") ? gradientList :
                        type.equals("French Tip") ? frenchList : catEyeList;

        generateColorPalette(targetList);

        if (!targetList.isEmpty()) {
            selectedColorHex = targetList.get(0).getHex();
        }
    }

    private void setTabButtonStyle(Button button, String bgColorHex, String textColorHex, boolean isActive) {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadius(24f);
        shape.setColor(Color.parseColor(bgColorHex));
        if (!isActive) {
            shape.setStroke(2, Color.parseColor("#E0E0E0"));
        }
        button.setBackground(shape);
        button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(bgColorHex)));
        button.setTextColor(Color.parseColor(textColorHex));
    }

    private void updateFinishState(String type) {
        selectedFinish = type;
        LinearLayout[] buttons = {btnGlossy, btnMatte, btnChrome, btnGlitter};
        for (LinearLayout btn : buttons) {
            if (btn != null) setFinishCardStyle(btn, false);
        }
        LinearLayout active = null;
        if (type.equals("Glossy")) active = btnGlossy;
        else if (type.equals("Matte")) active = btnMatte;
        else if (type.equals("Chrome")) active = btnChrome;
        else if (type.equals("Glitter")) active = btnGlitter;
        if (active != null) setFinishCardStyle(active, true);
    }

    private void setFinishCardStyle(LinearLayout layout, boolean isActive) {
        if (layout == null) return;
        TextView textView = null;
        ImageView imageView = null;
        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);
            if (child instanceof TextView) textView = (TextView) child;
            if (child instanceof ImageView) imageView = (ImageView) child;
        }
        GradientDrawable gd = new GradientDrawable();
        gd.setShape(GradientDrawable.RECTANGLE);
        gd.setCornerRadius(32f);
        if (isActive) {
            gd.setColor(Color.parseColor("#7A75F0"));
            layout.setBackground(gd);
            if (textView != null) textView.setTextColor(Color.WHITE);
        } else {
            gd.setColor(Color.WHITE);
            gd.setStroke(2, Color.parseColor("#E0E0E0"));
            layout.setBackground(gd);
            if (textView != null) textView.setTextColor(Color.parseColor("#222222"));
        }
    }

    private void setupAddonToggle(LinearLayout layout, String name) {
        if (layout == null) return;
        setAddonCardStyle(layout, false);
        layout.setOnClickListener(v -> {
            if (selectedAddonsList.contains(name)) {
                selectedAddonsList.remove(name);
                setAddonCardStyle(layout, false);
            } else {
                selectedAddonsList.add(name);
                setAddonCardStyle(layout, true);
            }
        });
    }

    private void setAddonCardStyle(LinearLayout layout, boolean isActive) {
        if (layout == null) return;
        TextView textView = null;
        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);
            if (child instanceof TextView) textView = (TextView) child;
        }
        GradientDrawable gd = new GradientDrawable();
        gd.setShape(GradientDrawable.RECTANGLE);
        gd.setCornerRadius(32f);
        if (isActive) {
            gd.setColor(Color.parseColor("#7A75F0"));
            layout.setBackground(gd);
            if (textView != null) textView.setTextColor(Color.WHITE);
        } else {
            gd.setColor(Color.WHITE);
            gd.setStroke(2, Color.parseColor("#E0E0E0"));
            layout.setBackground(gd);
            if (textView != null) textView.setTextColor(Color.parseColor("#222222"));
        }
    }

    // Helper Model Class agar tidak merusak struktur manifest/file luar
    public static class NailColor {
        private final String name;
        private final String hex;

        public NailColor(String name, String hex) {
            this.name = name;
            this.hex = hex;
        }

        public String getName() { return name; }
        public String getHex() { return hex; }
    }
}