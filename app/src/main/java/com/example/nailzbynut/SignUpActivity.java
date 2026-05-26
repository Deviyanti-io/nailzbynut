package com.example.nailzbynut;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private EditText etRegEmail, etRegUsername, etRegPassword;
    private Button btnRegister;
    private TextView tvGoToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etRegEmail = findViewById(R.id.et_reg_email);
        etRegUsername = findViewById(R.id.et_reg_username);
        etRegPassword = findViewById(R.id.et_reg_password);
        btnRegister = findViewById(R.id.btn_register);
        tvGoToLogin = findViewById(R.id.tv_go_to_login);

        btnRegister.setOnClickListener(v -> {
            String email = etRegEmail.getText().toString().trim();
            String username = etRegUsername.getText().toString().trim();
            String password = etRegPassword.getText().toString().trim();

            if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Semua kolom pendaftaran wajib diisi, Cutie!", Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferences sharedPref = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("SAVED_USER", username);
                editor.putString("SAVED_EMAIL", email);
                editor.putString("SAVED_PASS", password);
                editor.apply();

                Toast.makeText(this, "Pendaftaran sukses! Silakan Login 💖", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        tvGoToLogin.setOnClickListener(v -> finish());
    }
}