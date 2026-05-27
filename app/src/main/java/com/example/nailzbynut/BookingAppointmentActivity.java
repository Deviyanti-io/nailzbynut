package com.example.nailzbynut;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

public class BookingAppointmentActivity extends AppCompatActivity {

    private ImageView btnBack;
    private Button btnNextBooking;
    private CalendarView calendarView;
    private Button btnTime09, btnTime11, btnTime13, btnTime14, btnTime15, btnTime17;
    private String selectedDate = "";
    private String selectedTime = "";

    private String shape, length, colorType, colorHex, finish, sizeReport, notes, uploadedImage;
    private ArrayList<String> addonsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_appointment);

        Intent intent = getIntent();
        shape = intent.getStringExtra("FINAL_SHAPE");
        if (shape == null) shape = "Almond";
        length = intent.getStringExtra("FINAL_LENGTH");
        if (length == null) length = "Medium";
        colorType = intent.getStringExtra("FINAL_COLOR_TYPE");
        if (colorType == null) colorType = "Solid";
        colorHex = intent.getStringExtra("FINAL_COLOR");
        if (colorHex == null) colorHex = "#D6001C";
        finish = intent.getStringExtra("FINAL_FINISH");
        if (finish == null) finish = "Glossy";
        sizeReport = intent.getStringExtra("FINAL_SIZE");
        if (sizeReport == null) sizeReport = "M (16-12-13-12-10mm)";
        notes = intent.getStringExtra("FINAL_NOTES");
        if (notes == null) notes = "";
        uploadedImage = intent.getStringExtra("FINAL_IMAGE");
        if (uploadedImage == null) uploadedImage = "";
        addonsList = intent.getStringArrayListExtra("FINAL_ADDONS");
        if (addonsList == null) addonsList = new ArrayList<>();

        // Debug
        Toast.makeText(this, "Warna dari review: " + colorHex, Toast.LENGTH_LONG).show();

        btnBack = findViewById(R.id.btnBack);
        btnNextBooking = findViewById(R.id.btnNextBooking);
        calendarView = findViewById(R.id.calendarView);
        btnTime09 = findViewById(R.id.btnTime09);
        btnTime11 = findViewById(R.id.btnTime11);
        btnTime13 = findViewById(R.id.btnTime13);
        btnTime14 = findViewById(R.id.btnTime14);
        btnTime15 = findViewById(R.id.btnTime15);
        btnTime17 = findViewById(R.id.btnTime17);

        btnBack.setOnClickListener(v -> finish());

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
            Toast.makeText(this, "Tanggal: " + selectedDate, Toast.LENGTH_SHORT).show();
        });

        resetAllTimeButtons();
        setTimeButtonListeners();

        btnNextBooking.setOnClickListener(v -> {
            if (selectedTime.isEmpty() || selectedDate.isEmpty()) {
                Toast.makeText(this, "Pilih tanggal dan waktu!", Toast.LENGTH_SHORT).show();
                return;
            }

            String[] dateParts = selectedDate.split("/");
            String day = dateParts[0];
            int monthNum = Integer.parseInt(dateParts[1]);
            String monthName = getMonthName(monthNum);

            String[] timeParts = selectedTime.split("\\.");
            int startHour = Integer.parseInt(timeParts[0]);
            int startMin = Integer.parseInt(timeParts[1]);
            int endHour = startHour + 1;
            int endMin = startMin + 30;
            if (endMin >= 60) {
                endHour++;
                endMin -= 60;
            }
            String timeRange = String.format("%02d.%02d - %02d.%02d", startHour, startMin, endHour, endMin);

            String subtitle = shape + " • " + length + " • " + colorType;

            SharedPreferences prefs = getSharedPreferences("FinalBookingData", MODE_PRIVATE);
            String existingJson = prefs.getString("bookings_list", "[]");
            try {
                JSONArray bookingsArray = new JSONArray(existingJson);
                JSONObject newBooking = new JSONObject();
                newBooking.put("date", day);
                newBooking.put("month", monthName);
                newBooking.put("title", "Custom Nail");
                newBooking.put("subtitle", subtitle);
                newBooking.put("time", timeRange);
                newBooking.put("artist", "Nut (Top Artist)");
                newBooking.put("status", "Confirmed");
                newBooking.put("colorHex", colorHex);
                bookingsArray.put(newBooking);
                prefs.edit().putString("bookings_list", bookingsArray.toString()).apply();
                Toast.makeText(this, "Booking berhasil! Warna: " + colorHex, Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Gagal simpan", Toast.LENGTH_SHORT).show();
            }

            Intent homeIntent = new Intent(BookingAppointmentActivity.this, MainNavigationActivity.class);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(homeIntent);
            finish();
        });
    }

    private void setTimeButtonListeners() {
        btnTime09.setOnClickListener(v -> selectTimeSlot("09.00", btnTime09));
        btnTime11.setOnClickListener(v -> selectTimeSlot("11.00", btnTime11));
        btnTime13.setOnClickListener(v -> selectTimeSlot("13.00", btnTime13));
        btnTime14.setOnClickListener(v -> selectTimeSlot("14.00", btnTime14));
        btnTime15.setOnClickListener(v -> selectTimeSlot("15.00", btnTime15));
        btnTime17.setOnClickListener(v -> selectTimeSlot("17.00", btnTime17));
    }

    private String getMonthName(int month) {
        String[] months = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
        return months[month - 1];
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

    private void setPureRoundedButton(Button button, String bgHex, String textHex, boolean isActive) {
        if (button == null) return;
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadius(24f);
        shape.setColor(Color.parseColor(bgHex));
        if (!isActive) shape.setStroke(2, Color.parseColor("#E0E0E0"));
        button.setBackground(shape);
        button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(bgHex)));
        button.setTextColor(Color.parseColor(textHex));
    }
}