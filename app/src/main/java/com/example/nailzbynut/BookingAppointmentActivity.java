package com.example.nailzbynut;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class BookingAppointmentActivity extends AppCompatActivity {

    private ImageView btnBack;
    private Button btnNextBooking;
    private CalendarView calendarView;

    private Button btnTime09, btnTime11, btnTime13, btnTime14, btnTime15, btnTime17;

    private String selectedDate = "";
    private String selectedTime = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_appointment);

        btnBack = findViewById(R.id.btnBack);
        btnNextBooking = findViewById(R.id.btnNextBooking);
        calendarView = findViewById(R.id.calendarView);

        btnTime09 = findViewById(R.id.btnTime09);
        btnTime11 = findViewById(R.id.btnTime11);
        btnTime13 = findViewById(R.id.btnTime13);
        btnTime14 = findViewById(R.id.btnTime14);
        btnTime15 = findViewById(R.id.btnTime15);
        btnTime17 = findViewById(R.id.btnTime17);

        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        if (calendarView != null) {
            calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
                selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                Toast.makeText(this, "Tanggal dipilih: " + selectedDate, Toast.LENGTH_SHORT).show();
            });
        }

        // Set semua tombol jam menjadi bentuk bulat melengkung mewah saat halaman pertama dibuka
        resetAllTimeButtons();

        if (btnTime09 != null) btnTime09.setOnClickListener(v -> selectTimeSlot("09.00", btnTime09));
        if (btnTime11 != null) btnTime11.setOnClickListener(v -> selectTimeSlot("11.00", btnTime11));
        if (btnTime13 != null) btnTime13.setOnClickListener(v -> selectTimeSlot("13.00", btnTime13));
        if (btnTime14 != null) btnTime14.setOnClickListener(v -> selectTimeSlot("14.00", btnTime14));
        if (btnTime15 != null) btnTime15.setOnClickListener(v -> selectTimeSlot("15.00", btnTime15));
        if (btnTime17 != null) btnTime17.setOnClickListener(v -> selectTimeSlot("17.00", btnTime17));

        if (btnNextBooking != null) {
            btnNextBooking.setOnClickListener(v -> {
                if (selectedTime.isEmpty()) {
                    Toast.makeText(this, "Silakan pilih waktu booking terlebih dahulu!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(this, "Booking Berhasil Dikonfirmasi!", Toast.LENGTH_SHORT).show();

                // Menuju kembali ke dashboard utama setelah pemesanan kuku kustom selesai sukses
                Intent intent = new Intent(BookingAppointmentActivity.this, MainNavigationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            });
        }
    }

    private void resetAllTimeButtons() {
        setPureRoundedButton(btnTime09, "#FFFFFF", "#222222", false);
        setPureRoundedButton(btnTime11, "#FFFFFF", "#222222", false);
        setPureRoundedButton(btnTime13, "#FFFFFF", "#222222", false);
        setPureRoundedButton(btnTime14, "#FFFFFF", "#222222", false);
        setPureRoundedButton(btnTime15, "#FFFFFF", "#222222", false);
        setPureRoundedButton(btnTime17, "#FFFFFF", "#222222", false);
    }

    private void selectTimeSlot(String timeValue, Button activeButton) {
        selectedTime = timeValue;
        resetAllTimeButtons();
        setPureRoundedButton(activeButton, "#7A75F0", "#FFFFFF", true);
    }

    private void setPureRoundedButton(Button button, String backgroundColorHex, String textColorHex, boolean isActive) {
        if (button == null) return;

        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadius(24f); // Menjaga kelengkungan sudut jam agar selalu oval bulat indah
        shape.setColor(Color.parseColor(backgroundColorHex));

        if (!isActive) {
            shape.setStroke(2, Color.parseColor("#E0E0E0")); // Stroke outline abu tipis saat pasif
        }

        button.setBackground(shape);
        button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(backgroundColorHex)));
        button.setTextColor(Color.parseColor(textColorHex));
    }
}