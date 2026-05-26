package com.example.nailzbynut;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileFragment extends Fragment {

    TextView tvUserName, tvUserEmail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        tvUserName = view.findViewById(R.id.tvUserName);
        tvUserEmail = view.findViewById(R.id.tvUserEmail);

        // Perbaikan: gunakan UserPrefs yang sama dengan LoginActivity & SignUpActivity
        SharedPreferences sharedPref = requireActivity().getSharedPreferences("UserPrefs", getContext().MODE_PRIVATE);
        String username = sharedPref.getString("SAVED_USER", "Guest");
        String email = sharedPref.getString("SAVED_EMAIL", "guest@gmail.com");

        tvUserName.setText(username);
        tvUserEmail.setText(email);

        return view;
    }
}