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
import java.util.ArrayList;
import java.util.UUID;

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

            // Menyatukan list aksesoris addon ke dalam kalimat teks
            StringBuilder addonsBuilder = new StringBuilder();
            if (addonsList != null && !addonsList.isEmpty()) {
                for (int i = 0; i < addonsList.size(); i++) {
                    addonsBuilder.append(addonsList.get(i));
                    if (i < addonsList.size() - 1) addonsBuilder.append(", ");
                }
            } else {
                addonsBuilder.append("Tanpa Aksesoris");
            }

            // Gabungkan data pilihan asli ke string deskripsi subtitle
            String subtitle = shape + " • " + length + " • " + colorType + " (" + addonsBuilder.toString() + ")";
            String bookingId = UUID.randomUUID().toString();

            SharedPreferences prefs = getSharedPreferences("SimpleBookingData", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            editor.putString(bookingId + "_date", day);
            editor.putString(bookingId + "_month", monthName);
            editor.putString(bookingId + "_title", "Custom Nail");
            editor.putString(bookingId + "_subtitle", subtitle);
            editor.putString(bookingId + "_time", timeRange);
            editor.putString(bookingId + "_artist", "Nut (Top Artist)");
            editor.putString(bookingId + "_status", "Confirmed");
            editor.putString(bookingId + "_colorHex", colorHex);

            String existingIds = prefs.getString("booking_ids", "");
            String newIds = existingIds + (existingIds.isEmpty() ? "" : ",") + bookingId;
            editor.putString("booking_ids", newIds);

            boolean success = editor.commit();

            if (success) {
                Toast.makeText(this, "Booking sukses!", Toast.LENGTH_SHORT).show();
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