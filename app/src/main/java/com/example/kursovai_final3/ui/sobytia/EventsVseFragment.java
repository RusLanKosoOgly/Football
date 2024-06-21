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
import com.example.kursovai_final3.databinding.FragmentEventsvseBinding;
import com.example.kursovai_final3.ui.models.Sob;

import java.util.List;

public class EventsVseFragment extends Fragment {
    private FragmentEventsvseBinding binding;
    private SobDataBase sobDataBase;
    private SobDao sobDao;
    private RecyclerView recyclerView;
    NavController navController;
    private EventsVseAdapter eventsVseAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentEventsvseBinding.inflate(inflater, container, false);
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

    private void loadData() {
        List<Sob> sobList = sobDao.getAllSobs();
        eventsVseAdapter = new EventsVseAdapter(sobList, this::onItemClick, sobDao);
        recyclerView.setAdapter(eventsVseAdapter);
    }

    private void onItemClick(Sob sob) {
        SobytiaDescFragment fragment = SobytiaDescFragment.newInstance(sob);
        getParentFragmentManager().beginTransaction()
                .replace(R.id.nav_host_fragment_activity_main, fragment)
                .addToBackStack(null)
                .commit();
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnBack.setOnClickListener(v->{
            navController = Navigation.findNavController(requireActivity(),
                    R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.action_Sob_to_HomeFragment);
        });
    }
}