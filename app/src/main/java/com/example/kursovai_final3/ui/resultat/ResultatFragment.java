package com.example.kursovai_final3.ui.resultat;

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
import com.example.kursovai_final3.ScoreAdapter;
import com.example.kursovai_final3.databinding.FragmentResultatBinding;
import com.example.kursovai_final3.ui.models.ScoreModel;

import java.util.ArrayList;
import java.util.List;

public class ResultatFragment extends Fragment {

    private FragmentResultatBinding binding;
    List<ScoreModel> list_score = new ArrayList<>();
    ScoreAdapter adapter;
    NavController navController;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentResultatBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        createList();
        adapter = new ScoreAdapter();
        adapter.setList_m(list_score);
        binding.rvResultat.setAdapter(adapter);


        return root;
     }
    private void createList() {
        list_score.add(new ScoreModel( R.drawable.barca, R.drawable.real, "1-0","asdasdads","asdasdasdasd","match1"));
        list_score.add(new ScoreModel( R.drawable.barca, R.drawable.real, "1-3","messi","rodrigo,vinisius","match2"));

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnBack.setOnClickListener(v->{
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





