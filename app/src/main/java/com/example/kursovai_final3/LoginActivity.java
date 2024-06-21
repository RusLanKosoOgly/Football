package com.example.kursovai_final3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kursovai_final3.AppDatabase;
import com.example.kursovai_final3.UserEntity;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEt, passwordEt;
    private Button loginBtn;
    private TextView goToRegisterActivityTv;
    private AppDatabase database;
    private static final String ADMIN_EMAIL = "admin";
    private static final String ADMIN_PASSWORD = "1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEt = findViewById(R.id.email_et);
        passwordEt = findViewById(R.id.password_et);
        loginBtn = findViewById(R.id.login_btn);
        goToRegisterActivityTv = findViewById(R.id.go_to_register_activity_tv); // Проверьте, что ID правильный

        // Инициализация Room Database
        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "user-database").build();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        goToRegisterActivityTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private void loginUser() {
        final String emailStr = emailEt.getText().toString();
        final String passwordStr = passwordEt.getText().toString();

        if (TextUtils.isEmpty(emailStr) || TextUtils.isEmpty(passwordStr)) {
            Toast.makeText(LoginActivity.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
        } else {
            // Проверьте, является ли пользователь администратором
            if (emailStr.equals("admin") && passwordStr.equals("123")) {
                startSplashActivity(true, null); // Передача информации о входе администратора
                return;
            }

            new Thread(new Runnable() {
                @Override
                public void run() {
                    UserEntity user = database.userDao().getUserByEmailAndPassword(emailStr, passwordStr);
                    if (user != null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                                startSplashActivity(false, user); // Передача информации о пользователе
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }).start();
        }
    }

    private void startSplashActivity(boolean isAdmin, @Nullable UserEntity user) {
        Intent intent = new Intent(LoginActivity.this, SplashActivity.class);
        intent.putExtra("isAdmin", isAdmin);
        if (user != null) {
            intent.putExtra("userId", user.getId());
            intent.putExtra("userEmail", user.getEmail());
            // Добавьте другие поля, если необходимо
        }
        startActivity(intent);
        finish();
    }

}

