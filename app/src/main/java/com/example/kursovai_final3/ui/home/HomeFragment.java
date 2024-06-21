package com.example.kursovai_final3.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.kursovai_final3.R;
import com.example.kursovai_final3.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private DrawerLayout drawerLayout;
    private boolean isAdmin = false;
    private static final String CORRECT_PASSWORD = "12345"; // Замените на правильный пароль

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        drawerLayout = binding.drawerLayout;
        ImageButton buttonOpenDrawer = binding.buttonOpenDrawer;

        ImageView indicator1 = root.findViewById(R.id.indicator_1);
        ImageView indicator2 = root.findViewById(R.id.indicator_2);
        ImageView indicator3 = root.findViewById(R.id.indicator_3);
        ImageView indicator4 = root.findViewById(R.id.indicator_4);

        indicator1.setOnClickListener(v -> binding.viewPager2.setCurrentItem(0));
        indicator2.setOnClickListener(v -> binding.viewPager2.setCurrentItem(1));
        indicator3.setOnClickListener(v -> binding.viewPager2.setCurrentItem(2));
        indicator4.setOnClickListener(v -> binding.viewPager2.setCurrentItem(3));

        buttonOpenDrawer.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        // Получаем информацию о статусе администратора из аргументов
        if (getArguments() != null) {
            isAdmin = getArguments().getBoolean("isAdmin", false);
            int userId = getArguments().getInt("userId", -1);
            String userEmail = getArguments().getString("userEmail");
            // Добавьте другие поля, если необходимо


        }
        View passView = root.findViewById(R.id.pass);
        passView.setOnClickListener(v -> showPasswordDialog());

        return root;

    }
    private void showPasswordDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getLayoutInflater().inflate(R.layout.dialog_password, null);
        EditText passwordEditText = view.findViewById(R.id.passwordEditText);
        Button confirmButton = view.findViewById(R.id.confirmButton);

        builder.setView(view);
        AlertDialog dialog = builder.create();

        confirmButton.setOnClickListener(v -> {
            String inputPassword = passwordEditText.getText().toString();
            if (CORRECT_PASSWORD.equals(inputPassword)) {
                dialog.dismiss();

                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main);
                    navController.navigate(R.id.AdminFragment);
                    drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                Toast.makeText(getActivity(), "Неправильный пароль", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }
    private void navigateToSobytiaFragment() {
        // Предполагается, что используется Navigation Component
        NavHostFragment.findNavController(this).navigate(R.id.action_homeFragment_to_SobytiaFragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button buttonResultat = view.findViewById(R.id.resultat);
        buttonResultat.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.resultatFragment);
            drawerLayout.closeDrawer(GravityCompat.START);
        });

        Button buttonIgroki = view.findViewById(R.id.igroki);
        buttonIgroki.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.IgrokFragment);
            drawerLayout.closeDrawer(GravityCompat.START);
        });

        Button buttonMatchi = view.findViewById(R.id.matchi);
        buttonMatchi.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.MatchFragment);
            drawerLayout.closeDrawer(GravityCompat.START);
        });

        Button buttonaddsob = view.findViewById(R.id.sob);
        buttonaddsob.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.Sob);
            drawerLayout.closeDrawer(GravityCompat.START);
        });


        Button buttonExit = view.findViewById(R.id.button_exit);
        buttonExit.setOnClickListener(v -> showExitConfirmationDialog());

        MainAdapter adapter = new MainAdapter();
        binding.viewPager2.setAdapter(adapter);
        requireActivity().getOnBackPressedDispatcher()
                .addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        requireActivity().finish();
                    }
                });
    }

    private void showExitConfirmationDialog() {
        new AlertDialog.Builder(getActivity())
                .setTitle("Выход")
                .setMessage("Вы точно хотите закрыть приложение?")
                .setPositiveButton("Да", (dialog, which) -> requireActivity().finish())
                .setNegativeButton("Нет", null)
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
