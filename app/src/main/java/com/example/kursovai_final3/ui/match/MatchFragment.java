package com.example.kursovai_final3.ui.match;


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
import com.example.kursovai_final3.databinding.FragmentMatchiBinding;
import com.example.kursovai_final3.ui.models.MatchModel;

import java.util.ArrayList;
import java.util.List;

public class MatchFragment extends Fragment {

    private FragmentMatchiBinding binding;
    List<MatchModel> list_score = new ArrayList<>();
    MatchAdapter adapter;
    NavController navController;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMatchiBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        createList();
        adapter = new MatchAdapter();
        adapter.setList_m(list_score);
        binding.rvMatchi.setAdapter(adapter);


        return root;
    }
    private void createList() {
        list_score.add(new MatchModel( R.drawable.barca, R.drawable.real,"Барселона","Реал Мадрид","Цена: 1000 сом" ));
        list_score.add(new MatchModel( R.drawable.ilbirs, R.drawable.dordoi,"Илбирс","Дордой","Цена: 500 сом" ));
        list_score.add(new MatchModel( R.drawable.ilbirs, R.drawable.abdysh,"Илбирс","Абдыш-Ата","Цена: 300 сом" ));
        list_score.add(new MatchModel( R.drawable.dordoi, R.drawable.abdysh,"Дордой","Абдыш-Ата","Цена: 300 сом" ));


    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnBack.setOnClickListener(v->{
            navController = Navigation.findNavController(requireActivity(),
                    R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.action_MatchFragment_to_homeFragment);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}





