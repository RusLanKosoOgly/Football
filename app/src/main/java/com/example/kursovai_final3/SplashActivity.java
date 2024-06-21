
package com.example.kursovai_final3;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        final boolean isAdmin = getIntent().getBooleanExtra("isAdmin", false);
        final int userId = getIntent().getIntExtra("userId", -1);
        final String userEmail = getIntent().getStringExtra("userEmail");
        // Добавьте другие поля, если необходимо

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                intent.putExtra("isAdmin", isAdmin);
                intent.putExtra("userId", userId);
                intent.putExtra("userEmail", userEmail);
                // Добавьте другие поля, если необходимо
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}
