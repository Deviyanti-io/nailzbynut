package com.example.nailzbynut;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    private ImageView imgProfile, btnCamera, ivMemberIcon;
    private TextView tvGreeting, txtName, txtEmail, txtPhone, txtMember;
    private RelativeLayout memberCard;
    private LinearLayout menuEdit, menuWishlist, menuTreatment, menuSetting, menuLogout;
    private SharedPreferences sharedPreferences;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Inisialisasi Komponen Info Utama Profile
        imgProfile = view.findViewById(R.id.imgProfile);
        btnCamera = view.findViewById(R.id.btnCamera);
        tvGreeting = view.findViewById(R.id.tv_greeting);
        txtName = view.findViewById(R.id.txtName);
        txtEmail = view.findViewById(R.id.txtEmail);
        txtPhone = view.findViewById(R.id.txtPhone);

        // Inisialisasi Komponen Member Card
        memberCard = view.findViewById(R.id.memberCard);
        ivMemberIcon = view.findViewById(R.id.iv_member_icon);
        txtMember = view.findViewById(R.id.txtMember);

        // Inisialisasi Komponen Menu List
        menuEdit = view.findViewById(R.id.menuEdit);
        menuWishlist = view.findViewById(R.id.menuWishlist);
        menuTreatment = view.findViewById(R.id.menuTreatment);
        menuSetting = view.findViewById(R.id.menuSetting);
        menuLogout = view.findViewById(R.id.menuLogout);

        if (getContext() != null) {
            sharedPreferences = getContext().getSharedPreferences("UserSession", Context.MODE_PRIVATE);
        }

        // Memuat Data Sesi User Login
        loadUserData();

        // Setup Aksi Navigasi & Klik Interaktif
        setupMenuClickListeners();

        return view;
    }

    private void loadUserData() {
        if (sharedPreferences != null) {
            String loggedInName = sharedPreferences.getString("USER_NAME", "Putri Deviyanti");
            String loggedInEmail = sharedPreferences.getString("USER_EMAIL", "deviyanti@instiki.ac.id");
            String loggedInPhone = sharedPreferences.getString("USER_PHONE", "+62 812-3456-7890");
            String membershipLevel = sharedPreferences.getString("MEMBER_LEVEL", "Silver");

            if (txtName != null) txtName.setText(loggedInName);
            if (txtEmail != null) txtEmail.setText(loggedInEmail);
            if (txtPhone != null) txtPhone.setText(loggedInPhone);

            if (tvGreeting != null) {
                tvGreeting.setText("Hi, " + loggedInName + " 👋");
            }

            updateMembershipUI(membershipLevel);
        }
    }

    private void updateMembershipUI(String level) {
        if (txtMember != null) {
            txtMember.setText(level);
        }

        if (ivMemberIcon == null || memberCard == null || txtMember == null) return;

        switch (level.toLowerCase()) {
            case "gold":
                ivMemberIcon.setImageResource(R.drawable.silver_bg); // Sesuaikan dengan nama drawable-mu
                memberCard.setBackgroundTintList(android.content.res.ColorStateList.valueOf(android.graphics.Color.parseColor("#FFF2CC")));
                txtMember.setBackgroundTintList(android.content.res.ColorStateList.valueOf(android.graphics.Color.parseColor("#E6B800")));
                break;
            case "platinum":
                ivMemberIcon.setImageResource(R.drawable.silver_bg);
                memberCard.setBackgroundTintList(android.content.res.ColorStateList.valueOf(android.graphics.Color.parseColor("#E2F0D9")));
                txtMember.setBackgroundTintList(android.content.res.ColorStateList.valueOf(android.graphics.Color.parseColor("#70AD47")));
                break;
            case "silver":
            default:
                ivMemberIcon.setImageResource(R.drawable.silver_bg);
                memberCard.setBackgroundTintList(android.content.res.ColorStateList.valueOf(android.graphics.Color.parseColor("#E5DEFF")));
                txtMember.setBackgroundTintList(android.content.res.ColorStateList.valueOf(android.graphics.Color.parseColor("#988DE4")));
                break;
        }
    }

    private void setupMenuClickListeners() {
        btnCamera.setOnClickListener(v -> showEditProfileDialog());
        menuEdit.setOnClickListener(v -> showEditProfileDialog());

        // 2. WISHLIST - Bersih dari teks sisa cetakan kutipan AI
        menuWishlist.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), WishlistActivity.class);
            startActivity(intent);
        });

        // 3. MY TREATMENT - Bersih dari teks sisa cetakan kutipan AI
        menuTreatment.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), BookingAppointmentActivity.class);
            startActivity(intent);
        });

        // 4. PENGATURAN
        menuSetting.setOnClickListener(v -> showSettingsDialog());

        // 5. LOG OUT - Bersih dari teks sisa cetakan kutipan AI
        menuLogout.setOnClickListener(v -> {
            if (sharedPreferences != null) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

                Toast.makeText(getActivity(), "Berhasil Log Out", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showEditProfileDialog() {
        if (getActivity() == null) return;

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Edit Profil");

        LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 40, 50, 10);

        final EditText inputName = new EditText(getActivity());
        inputName.setHint("Nama Lengkap");
        inputName.setText(txtName.getText().toString());
        layout.addView(inputName);

        final EditText inputPhone = new EditText(getActivity());
        inputPhone.setHint("Nomor Telepon");
        inputPhone.setText(txtPhone.getText().toString());
        layout.addView(inputPhone);

        final EditText inputEmail = new EditText(getActivity());
        inputEmail.setHint("Email");
        inputEmail.setText(txtEmail.getText().toString());
        layout.addView(inputEmail);

        builder.setView(layout);

        builder.setPositiveButton("Simpan", (dialogInterface, i) -> {
            String newName = inputName.getText().toString();
            String newPhone = inputPhone.getText().toString();
            String newEmail = inputEmail.getText().toString();

            if (sharedPreferences != null) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("USER_NAME", newName);
                editor.putString("USER_PHONE", newPhone);
                editor.putString("USER_EMAIL", newEmail);
                editor.apply();
            }

            txtName.setText(newName);
            txtPhone.setText(newPhone);
            txtEmail.setText(newEmail);
            if (tvGreeting != null) {
                tvGreeting.setText("Hi, " + newName + " 👋");
            }

            Toast.makeText(getActivity(), "Profil berhasil diperbarui!", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Batal", (dialogInterface, i) -> dialogInterface.cancel());
        builder.show();
    }

    private void showSettingsDialog() {
        if (getActivity() == null) return;

        String[] options = {"Ubah Bahasa", "Ubah Tema (Gelap/Terang)", "Pemberitahuan"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Pengaturan");

        builder.setItems(options, (dialogInterface, i) -> {
            if (i == 0) {
                Toast.makeText(getActivity(), "Fitur Pengaturan Bahasa dipilih", Toast.LENGTH_SHORT).show();
            } else if (i == 1) {
                Toast.makeText(getActivity(), "Fitur Pengaturan Tema dipilih", Toast.LENGTH_SHORT).show();
            } else if (i == 2) {
                Toast.makeText(getActivity(), "Fitur Pengaturan Pemberitahuan dipilih", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }
}