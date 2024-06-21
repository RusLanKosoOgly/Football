package com.example.kursovai_final3.ui.description;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.kursovai_final3.R;
import com.example.kursovai_final3.databinding.FragmentDescriptionBinding;
import com.example.kursovai_final3.ui.models.ScoreModel;

import java.util.ArrayList;

public class DescFragment extends Fragment {

    private FragmentDescriptionBinding binding;
    private ArrayList<ScoreModel> d_list = new ArrayList<>();
    private DescAdapter adapter;
    private NavController navController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new DescAdapter();

        if (getArguments() != null) {
            d_list = getArguments().getParcelableArrayList("favorite");
        } else {
            Toast.makeText(requireActivity(), "No data available", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDescriptionBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (d_list != null) {
            adapter.setListD(d_list);
        } else {
            Toast.makeText(requireActivity(), "No data selected", Toast.LENGTH_SHORT).show();
        }

        binding.rvDetailsCatalog.setAdapter(adapter);

        binding.btnBack.setOnClickListener(v -> {
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.action_descFragment_to_resultatFragment);
        });
    }
}
