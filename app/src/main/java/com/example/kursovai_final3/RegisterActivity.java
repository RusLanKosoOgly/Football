package com.example.kursovai_final3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kursovai_final3.AppDatabase;
import com.example.kursovai_final3.UserEntity;

public class RegisterActivity extends AppCompatActivity {

    Button btnSignUp;
    AppDatabase database;

    private EditText email, username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnSignUp = findViewById(R.id.sign_up_btn);

        // Инициализация Room Database
        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "user-database").build();

        email = findViewById(R.id.email_et);
        username = findViewById(R.id.username_et);
        password = findViewById(R.id.password_et);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        final String emailStr = email.getText().toString();
        final String usernameStr = username.getText().toString();
        final String passwordStr = password.getText().toString();

        if (TextUtils.isEmpty(emailStr) || TextUtils.isEmpty(usernameStr) || TextUtils.isEmpty(passwordStr)) {
            Toast.makeText(RegisterActivity.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
        } else {
            // Проверяем, есть ли пользователь с таким же email в базе данных Room
            new Thread(new Runnable() {
                @Override
                public void run() {
                    UserEntity existingUser = database.userDao().getUserByEmailAndPassword(emailStr,passwordStr);
                    if (existingUser != null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegisterActivity.this, "User with this email already exists", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        // Регистрация нового пользователя
                        UserEntity newUser = new UserEntity(emailStr, usernameStr, passwordStr);
                        database.userDao().insertUser(newUser);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                finish();
                            }
                        });
                    }
                }
            }).start();
        }
    }
}

