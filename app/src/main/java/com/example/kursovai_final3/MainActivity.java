package com.example.kursovai_final3;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.kursovai_final3.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);

        boolean isAdmin = getIntent().getBooleanExtra("isAdmin", false);
        int userId = getIntent().getIntExtra("userId", -1);
        String userEmail = getIntent().getStringExtra("userEmail");
        // Добавьте другие поля, если необходимо

        Bundle bundle = new Bundle();
        bundle.putBoolean("isAdmin", isAdmin);
        bundle.putInt("userId", userId);
        bundle.putString("userEmail", userEmail);
        // Добавьте другие поля, если необходимо

        navController.setGraph(R.navigation.mobile_navigation, bundle);
    }
}

