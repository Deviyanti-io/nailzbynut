package com.example.nailzbynut;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin;
    private TextView tvGoToSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
        tvGoToSignUp = findViewById(R.id.tv_go_to_signup);

        if (btnLogin != null) {
            btnLogin.setOnClickListener(v -> {
                String inputName = etUsername != null ? etUsername.getText().toString().trim() : "";
                String inputPass = etPassword != null ? etPassword.getText().toString().trim() : "";

                if (inputName.isEmpty() || inputPass.isEmpty()) {
                    Toast.makeText(this, "Username dan Password wajib diisi ya, Cutie!", Toast.LENGTH_SHORT).show();
                    return;
                }

                SharedPreferences sharedPref = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                String savedUsername = sharedPref.getString("SAVED_USER", "");
                String savedPassword = sharedPref.getString("SAVED_PASS", "");

                if (inputName.equalsIgnoreCase(savedUsername) && inputPass.equals(savedPassword)) {
                    Toast.makeText(this, "Login Berhasil! 💖", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(LoginActivity.this, MainNavigationActivity.class);
                    intent.putExtra("USER_NAME", inputName);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "Username atau Password salah / belum terdaftar!", Toast.LENGTH_LONG).show();
                }
            });
        }

        if (tvGoToSignUp != null) {
            tvGoToSignUp.setOnClickListener(v -> {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            });
        }
    }
}