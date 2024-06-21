package com.example.kursovai_final3.ui.sobytia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.kursovai_final3.R;
import com.example.kursovai_final3.databinding.FragmentEventsBinding;
import com.example.kursovai_final3.ui.models.Sob;

import java.util.List;

public class EventsFragment extends Fragment {
    private FragmentEventsBinding binding;
    private SobDataBase sobDataBase;
    private SobDao sobDao;
    private RecyclerView recyclerView;
    private EventsAdapter eventsAdapter;
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentEventsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sobDataBase = Room.databaseBuilder(getContext(), SobDataBase.class, "database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        sobDao = sobDataBase.sobDao();

        recyclerView = binding.rvSob;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        loadData();

        return root;
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnBack.setOnClickListener(v -> {
            navController = Navigation.findNavController(requireActivity(),
                    R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.action_SobytiaFragment_to_AdminFragment);
        });
    }

    private void loadData() {
        List<Sob> sobList = sobDao.getAllSobs();
        eventsAdapter = new EventsAdapter(sobList, this::onItemClick, sobDao);
        recyclerView.setAdapter(eventsAdapter);
    }

    private void onItemClick(Sob sob) {
        SobytiaDescFragment fragment = SobytiaDescFragment.newInstance(sob);
        getParentFragmentManager().beginTransaction()
                .replace(R.id.nav_host_fragment_activity_main, fragment)
                .addToBackStack(null)
                .commit();
    }
}