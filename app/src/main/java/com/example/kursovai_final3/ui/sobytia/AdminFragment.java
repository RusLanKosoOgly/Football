package com.example.kursovai_final3.ui.sobytia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.kursovai_final3.R;

public class AdminFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_admin, container, false);

        Button buttonAddEvent = root.findViewById(R.id.button_add_event);
        Button buttonDeleteEvent = root.findViewById(R.id.button_delete_event);
        Button buttonBackEvent = root.findViewById(R.id.button_back_event);

        buttonAddEvent.setOnClickListener(this::navigateToAddEvent);
        buttonDeleteEvent.setOnClickListener(this::navigateToDeleteEvent);
        buttonBackEvent.setOnClickListener(this::navigateToBackEvent);

        return root;
    }

    private void navigateToAddEvent(View view) {
        NavController navController = Navigation.findNavController(view);
        navController.navigate(R.id.AddSobytieFragmnet);
    }

    private void navigateToDeleteEvent(View view) {
        NavController navController = Navigation.findNavController(view);
        navController.navigate(R.id.SobytiaFragment);
    }

    private void navigateToBackEvent(View view) {
        NavController navController = Navigation.findNavController(view);
        navController.navigate(R.id.navigation_home);
    }
}
