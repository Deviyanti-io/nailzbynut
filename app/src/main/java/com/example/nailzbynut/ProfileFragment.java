package com.example.nailzbynut;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView menuBooking = view.findViewById(R.id.menuBooking);
        Button btnSignOut = view.findViewById(R.id.btnSignOut);

        // Aksi menu My Bookings (Bisa diarahkan ke Riwayat/Activity Booking)
        menuBooking.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), BookingAppointmentActivity.class);
            startActivity(intent);
        });

        // Aksi Sign Out
        btnSignOut.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "Berhasil keluar dari akun Nut!", Toast.LENGTH_SHORT).show();
            // Logika pindah ke LoginActivity jika ada
        });

        return view;
    }
}