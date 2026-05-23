package com.example.nailzbynut;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    private TextView tvUserGreeting;
    private CardView btnCustomNailMenuCard;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate layout dashboard utama kamu
        View view = inflater.inflate(R.layout.activity_home, container, false);

        // Sinkronisasi ID XML
        tvUserGreeting = view.findViewById(R.id.tv_user_greeting);
        btnCustomNailMenuCard = view.findViewById(R.id.btn_menu_custom_nail);

        // Ganti teks "Hi, Nut" menjadi nama asli yang login
        if (getArguments() != null) {
            String namaUser = getArguments().getString("NAME_BUNDLE");
            if (tvUserGreeting != null && namaUser != null && !namaUser.isEmpty()) {
                tvUserGreeting.setText("Hi, " + namaUser + " ✨");
            }
        }

        // Hidupkan tombol klik menu Custom Nail di dalam dashboard fragment
        if (btnCustomNailMenuCard != null) {
            btnCustomNailMenuCard.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), CustomNailColorActivity.class);
                startActivity(intent);
            });
        }

        return view;
    }
}