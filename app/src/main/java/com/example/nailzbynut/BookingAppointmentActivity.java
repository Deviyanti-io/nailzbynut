package com.example.nailzbynut;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class BookingAppointmentActivity extends AppCompatActivity {

    private String selectedDate = "";
    private String selectedTime = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_appointment);

        ImageView btnBack = findViewById(R.id.btnBack);
        CalendarView calendarView = findViewById(R.id.calendarView);
        Button btnTime09 = findViewById(R.id.btnTime09);
        Button btnTime11 = findViewById(R.id.btnTime11);
        Button btnTime13 = findViewById(R.id.btnTime13);
        Button btnTime14 = findViewById(R.id.btnTime14);
        Button btnTime15 = findViewById(R.id.btnTime15);
        Button btnTime17 = findViewById(R.id.btnTime17);
        Button btnNextBooking = findViewById(R.id.btnNextBooking);

        btnBack.setOnClickListener(v -> finish());

        // Event listener saat user mengubah tanggal di kalender
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // Bulan dimulai dari 0 (Januari = 0, Juni = 5)
                selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                Toast.makeText(BookingAppointmentActivity.this, "Tanggal dipilih: " + selectedDate, Toast.LENGTH_SHORT).show();
            }
        });

        // Event listener untuk pilihan waktu slot jam
        btnTime09.setOnClickListener(v -> selectTime("09.00"));
        btnTime11.setOnClickListener(v -> selectTime("11.00"));
        btnTime13.setOnClickListener(v -> selectTime("13.00"));
        btnTime14.setOnClickListener(v -> selectTime("14.00"));
        btnTime15.setOnClickListener(v -> selectTime("15.00"));
        btnTime17.setOnClickListener(v -> selectTime("17.00"));

        // Validasi dan lanjut
        btnNextBooking.setOnClickListener(v -> {
            if (selectedDate.isEmpty()) {
                Toast.makeText(BookingAppointmentActivity.this, "Silakan pilih tanggal terlebih dahulu!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (selectedTime.isEmpty()) {
                Toast.makeText(BookingAppointmentActivity.this, "Silakan pilih waktu jam kunjungan!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Di sini kamu bisa memindahkan user ke activity berikutnya (misalnya halaman Ringkasan Metode Pembayaran)
            Toast.makeText(BookingAppointmentActivity.this, "Jadwal disimpan untuk " + selectedDate + " jam " + selectedTime, Toast.LENGTH_LONG).show();

            // Contoh intent ke langkah konfirmasi pembayaran (Metode Pembayaran/QRIS)
            // Intent intent = new Intent(BookingAppointmentActivity.this, PaymentMethodActivity.class);
            // intent.putExtra("BOOKING_DATE", selectedDate);
            // intent.putExtra("BOOKING_TIME", selectedTime);
            // startActivity(intent);
        });
    }

    private void selectTime(String time) {
        selectedTime = time;
        Toast.makeText(this, "Jam dipilih: " + time, Toast.LENGTH_SHORT).show();
    }
}