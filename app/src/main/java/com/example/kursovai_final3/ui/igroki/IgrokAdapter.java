package com.example.kursovai_final3.ui.igroki;


import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kursovai_final3.R;
import com.example.kursovai_final3.databinding.ItemIgrokBinding;
import com.example.kursovai_final3.ui.models.IgrokModel;

import java.util.ArrayList;
import java.util.List;

public class IgrokAdapter extends RecyclerView.Adapter<com.example.kursovai_final3.ui.igroki.IgrokAdapter.ViewHolder> {
    ItemIgrokBinding binding;
    List<IgrokModel> list_m = new ArrayList<>();
    private ArrayList<IgrokModel> selected_list = new ArrayList<>();
    NavController navController;

    public void setList_m(List<IgrokModel> list_m) {
        this.list_m = list_m;
    }

    @NonNull
    @Override
    public com.example.kursovai_final3.ui.igroki.IgrokAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemIgrokBinding
                .inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new com.example.kursovai_final3.ui.igroki.IgrokAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.kursovai_final3.ui.igroki.IgrokAdapter.ViewHolder holder, int position) {
        holder.onBind(list_m.get(position));
    }

    @Override
    public int getItemCount() {
        return list_m.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemIgrokBinding binding;

        public ViewHolder(@NonNull ItemIgrokBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void onBind(IgrokModel igrokModel) {
            binding.playerImage.setImageResource(igrokModel.getIgrokImageResId());
            binding.clubIcon.setImageResource(igrokModel.getKlubImageResId());
            binding.countryIcon.setImageResource(igrokModel.getStranaImageResId());

            binding.playerName.setText(igrokModel.getImya());
            binding.playerAge.setText(igrokModel.getAge());
            binding.playerGamesTotal.setText(igrokModel.getCarearGame());
            binding.playerGoalsTotal.setText(igrokModel.getCarearGoal());
            binding.playerAssistsTotal.setText(igrokModel.getCarearAsist());
            binding.playerGamesSeason.setText(igrokModel.getSeasonGame());
            binding.playerGoalsSeason.setText(igrokModel.getSeasonGoal());
            binding.playerAssistsSeason.setText(igrokModel.getSeasonAsist());






            binding.btnZoom.setOnClickListener(view -> {
                selected_list.add(igrokModel);
                navController = Navigation.findNavController((Activity) itemView.getContext(), R.id.nav_host_fragment_activity_main);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("favorite", selected_list);
                navController.navigate(R.id.DescIgrokFragment, bundle);
            });
        }
    }
}
