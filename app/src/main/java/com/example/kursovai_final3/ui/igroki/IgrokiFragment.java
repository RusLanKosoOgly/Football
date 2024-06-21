package com.example.kursovai_final3.ui.igroki;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.kursovai_final3.R;
import com.example.kursovai_final3.databinding.FragmentIgrokiBinding;
import com.example.kursovai_final3.ui.models.IgrokModel;

import java.util.ArrayList;
import java.util.List;

public class IgrokiFragment extends Fragment {

    private FragmentIgrokiBinding binding;
    List<IgrokModel> list_score = new ArrayList<>();
    IgrokAdapter adapter;
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentIgrokiBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        adapter = new IgrokAdapter();

        binding.rvIgroki.setAdapter(adapter);
        createList();

        return root;
    }

    private void createList() {
        list_score.add(new IgrokModel(R.drawable.argentina, R.drawable.maiami, R.drawable.messi, "Лионель Месси", "35", "Matchs: 1000", "Assists: 800", "Goals: 400", "Assists:10", "Goals:6", "Matchs:10"));
        list_score.add(new IgrokModel(R.drawable.kyrgyz, R.drawable.galatasarai, R.drawable.beknaz, "Бекназ Алмазбеков", "18", "Matchs: 100", "Assists: 20", "Goals: 100", "Assists:10", "Goals:36", "Matchs:40"));
        list_score.add(new IgrokModel(R.drawable.kyrgyz, R.drawable.enisei, R.drawable.kitchin, "Валерий Кичин", "31", "Matchs: 600", "Assists: 50", "Goals: 30", "Assists:10", "Goals:1", "Matchs:50"));
        list_score.add(new IgrokModel(R.drawable.kyrgyz, R.drawable.abdysh, R.drawable.brayzman, "Християн Браузман", "20", "Matchs: 100", "Assists: 10", "Goals: 5", "Assists:1", "Goals:1", "Matchs:40"));

        adapter.setList_m(list_score);
        adapter.notifyDataSetChanged(); // Уведомляем адаптер об изменении данных

        binding.progressBar.setVisibility(View.GONE); // Скрываем полоску загрузки
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnBack.setOnClickListener(v -> {
            navController = Navigation.findNavController(requireActivity(),
                    R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.action_resultatFragment_to_homeFragment);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
