package com.example.nailzbynut;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    private View[] circles = new View[29];

    private String selectedColorType = "Solid";
    private String selectedColorHex = "#D6001C"; // Default merah biar tidak null
    private String selectedFinish = "Glossy";
    private ArrayList<String> selectedAddonsList = new ArrayList<>();
    private String dataShape = "Almond", dataLength = "Medium"; // Default isi amandemen

    private String selectedImageUriString = "";
    private ActivityResultLauncher<Intent> galleryLauncher;

    private final String[] solidList = {
            "#000000", "#F2F0EB", "#D6001C", "#C4C2C0", "#D9A05B", "#F5D6C4", "#F5D1BC", "#E8A7B5", "#F7A399", "#E394A4",
            "#BA7EA9", "#4E2B6B", "#731822", "#E04D79", "#8F63A8", "#78388C", "#DED2F9", "#E8C5E5", "#8F8073", "#D1BCB2",
            "#008EA6", "#0B4273", "#9ED463", "#8B5A2B", "#118199", "#0F6E85", "#08576B", "#5785A6", "#1C364A"
    };

    private final String[] gradientList = {
            "#FF9A9E", "#FECFEF", "#A1C4FD", "#C2E9FB", "#F6D365", "#FDA085", "#E0C3FC", "#8EC5FC", "#84FAB0", "#8FD3F4",
            "#FFECD2", "#FCB69F", "#CFD9DF", "#E2EBEE", "#FFF1EB", "#ACE0F9", "#ACCBEE", "#E7F0FD", "#E0F7FA", "#FFF9C4",
            "#FFE082", "#FFD54F", "#FFC0CB", "#FFB6C1", "#B2FEFA", "#00C9FF", "#92FE9D", "#0575E6", "#FC466B"
    };

    private final String[] frenchList = {
            "#FFFFFF", "#FFE4E1", "#F5F5DC", "#FFD700", "#000000", "#E6E6FA", "#B0E0E6", "#FFB6C1", "#FFF8DC", "#E0FFFF",
            "#FAF0E6", "#D8BFD8", "#FFF0F5", "#FAEBD7", "#F0F8FF", "#F8F8FF", "#F0FFF0", "#FFFFF0", "#F0FFFF", "#FFF5EE",
            "#F5FFFA", "#F0F0F3", "#E8E8E8", "#DCDCDC", "#B5B5B5", "#CCCCCC", "#EAEAEA", "#D3D3D3", "#C0C0C0"
    };

    private final String[] catEyeList = {
            "#2E1A47", "#1A2E40", "#1A402E", "#403A1A", "#401A1A", "#331A47", "#1A3347", "#1A4733", "#473B1A", "#471A1A",
            "#3D1F5A", "#1F3D5A", "#1F5A3D", "#5A4A1F", "#5A1F1F", "#4A1F5A", "#1F4A5A", "#1F5A4A", "#5A521F", "#5A1F2D",
            "#5C2E8A", "#2E5C8A", "#2E8A5C", "#8A732E", "#8A2E2E", "#732E8A", "#2E738A", "#2E8A73", "#8A7E2E"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_nail_color);

        if (getIntent().getStringExtra("SHAPE_DATA") != null) {
            dataShape = getIntent().getStringExtra("SHAPE_DATA");
        }
        if (getIntent().getStringExtra("LENGTH_DATA") != null) {
            dataLength = getIntent().getStringExtra("LENGTH_DATA");
        }

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

        initCircleViews();
        btnBack.setOnClickListener(v -> finish());

        updateColorTypeState("Solid");
        updateFinishState("Glossy");

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

        if (btnUploadPhoto != null) {
            btnUploadPhoto.setOnClickListener(v -> {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                galleryLauncher.launch(galleryIntent);
            });
        }

        if (btnTypeSolid != null) btnTypeSolid.setOnClickListener(v -> updateColorTypeState("Solid"));
        if (btnTypeGradient != null) btnTypeGradient.setOnClickListener(v -> updateColorTypeState("Ombre"));
        if (btnTypeFrench != null) btnTypeFrench.setOnClickListener(v -> updateColorTypeState("French Tip"));
        if (btnTypeCatEye != null) btnTypeCatEye.setOnClickListener(v -> updateColorTypeState("Cat Eye"));

        if (btnGlossy != null) btnGlossy.setOnClickListener(v -> updateFinishState("Glossy"));
        if (btnMatte != null) btnMatte.setOnClickListener(v -> updateFinishState("Matte"));
        if (btnChrome != null) btnChrome.setOnClickListener(v -> updateFinishState("Chrome"));
        if (btnGlitter != null) btnGlitter.setOnClickListener(v -> updateFinishState("Glitter"));

        setupAddonToggle(findViewById(R.id.addon_charms), "Charms");
        setupAddonToggle(findViewById(R.id.addon_pearls), "Pearls");
        setupAddonToggle(findViewById(R.id.addon_rhinestone), "Rhinestone");
        setupAddonToggle(findViewById(R.id.addon_flower), "3D Flower");
        setupAddonToggle(findViewById(R.id.addon_stickers), "Stickers");
        setupAddonToggle(findViewById(R.id.addon_bow), "Bow");

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

    private void initCircleViews() {
        circles[0] = findViewById(R.id.c_001); circles[1] = findViewById(R.id.c_002); circles[2] = findViewById(R.id.c_003);
        circles[3] = findViewById(R.id.c_004); circles[4] = findViewById(R.id.c_005); circles[5] = findViewById(R.id.c_006);
        circles[6] = findViewById(R.id.c_007); circles[7] = findViewById(R.id.c_008); circles[8] = findViewById(R.id.c_009);
        circles[9] = findViewById(R.id.c_010); circles[10] = findViewById(R.id.c_011); circles[11] = findViewById(R.id.c_012);
        circles[12] = findViewById(R.id.c_013); circles[13] = findViewById(R.id.c_014); circles[14] = findViewById(R.id.c_015);
        circles[15] = findViewById(R.id.c_016); circles[16] = findViewById(R.id.c_017); circles[17] = findViewById(R.id.c_018);
        circles[18] = findViewById(R.id.c_019); circles[19] = findViewById(R.id.c_020); circles[20] = findViewById(R.id.c_021);
        circles[21] = findViewById(R.id.c_022); circles[22] = findViewById(R.id.c_023); circles[23] = findViewById(R.id.c_024);
        circles[24] = findViewById(R.id.c_025); circles[25] = findViewById(R.id.c_026); circles[26] = findViewById(R.id.c_027);
        circles[27] = findViewById(R.id.c_028); circles[28] = findViewById(R.id.c_029);

        for (int i = 0; i < circles.length; i++) {
            final int index = i;
            if (circles[i] != null) {
                circles[i].setOnClickListener(v -> {
                    String[] currentList = selectedColorType.equals("Solid") ? solidList :
                            selectedColorType.equals("Ombre") ? gradientList :
                                    selectedColorType.equals("French Tip") ? frenchList : catEyeList;
                    selectedColorHex = currentList[index];
                    Toast.makeText(this, "Warna dipilih: " + selectedColorHex, Toast.LENGTH_SHORT).show();
                });
            }
        }
    }

    private void updateColorTypeState(String type) {
        selectedColorType = type;
        Button[] tabs = {btnTypeSolid, btnTypeGradient, btnTypeFrench, btnTypeCatEye};
        for (Button b : tabs) {
            if (b != null) {
                b.setBackgroundResource(R.drawable.bg_button_outline);
                b.setTextColor(Color.parseColor("#1A1C29"));
            }
        }

        // PENGIKUT LOGIKA PROTEKSI AMAN (Biar Ombre tidak Null Pointer Crash)
        Button active = btnTypeSolid;
        if (type.equals("Ombre") && btnTypeGradient != null) active = btnTypeGradient;
        else if (type.equals("French Tip") && btnTypeFrench != null) active = btnTypeFrench;
        else if (type.equals("Cat Eye") && btnTypeCatEye != null) active = btnTypeCatEye;

        if (active != null) {
            active.setBackgroundResource(R.drawable.bg_button_rounded);
            active.setTextColor(Color.WHITE);
        }

        String[] targetList = type.equals("Solid") ? solidList : type.equals("Ombre") ? gradientList : type.equals("French Tip") ? frenchList : catEyeList;
        for (int i = 0; i < circles.length; i++) {
            if (circles[i] != null) {
                circles[i].setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(targetList[i])));
            }
        }
    }

    private void updateFinishState(String type) {
        selectedFinish = type;
        LinearLayout[] buttons = {btnGlossy, btnMatte, btnChrome, btnGlitter};
        for (LinearLayout btn : buttons) {
            if (btn != null) {
                btn.setBackgroundColor(Color.WHITE);
                ((TextView) btn.getChildAt(1)).setTextColor(Color.parseColor("#1A1C29"));
                if (btn.getChildAt(0) instanceof ImageView) {
                    ((ImageView) btn.getChildAt(0)).setImageTintList(ColorStateList.valueOf(Color.parseColor("#1A1C29")));
                }
            }
        }

        LinearLayout active = type.equals("Glossy") ? btnGlossy : type.equals("Matte") ? btnMatte : type.equals("Chrome") ? btnChrome : btnGlitter;
        if (active != null) {
            active.setBackgroundColor(Color.parseColor("#F4F3FF"));
            ((TextView) active.getChildAt(1)).setTextColor(Color.parseColor("#5A55CA"));
            if (active.getChildAt(0) instanceof ImageView) {
                ((ImageView) active.getChildAt(0)).setImageTintList(ColorStateList.valueOf(Color.parseColor("#5A55CA")));
            }
        }
    }

    private void setupAddonToggle(LinearLayout layout, String name) {
        if (layout == null) return;
        layout.setOnClickListener(v -> {
            if (selectedAddonsList.contains(name)) {
                selectedAddonsList.remove(name);
                layout.setBackgroundColor(Color.WHITE);
                ((TextView) layout.getChildAt(1)).setTextColor(Color.parseColor("#1A1C29"));
                if (layout.getChildAt(0) instanceof ImageView) {
                    ((ImageView) layout.getChildAt(0)).setImageTintList(ColorStateList.valueOf(Color.parseColor("#1A1C29")));
                }
            } else {
                selectedAddonsList.add(name);
                layout.setBackgroundColor(Color.parseColor("#F4F3FF"));
                ((TextView) layout.getChildAt(1)).setTextColor(Color.parseColor("#5A55CA"));
                if (layout.getChildAt(0) instanceof ImageView) {
                    ((ImageView) layout.getChildAt(0)).setImageTintList(ColorStateList.valueOf(Color.parseColor("#5A55CA")));
                }
            }
        });
    }
}