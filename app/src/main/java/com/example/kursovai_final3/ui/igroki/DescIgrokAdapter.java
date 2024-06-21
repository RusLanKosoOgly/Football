package com.example.kursovai_final3.ui.igroki;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kursovai_final3.databinding.ItemDescIgrokBinding;
import com.example.kursovai_final3.ui.models.IgrokModel;

import java.util.ArrayList;
import java.util.List;

public class DescIgrokAdapter extends RecyclerView.Adapter<com.example.kursovai_final3.ui.igroki.DescIgrokAdapter.ViewHolder> {

    ItemDescIgrokBinding binding;

    List<IgrokModel> listD = new ArrayList<>();
    public void setListD(List<IgrokModel> listD) {
        this.listD = listD;

    }

    @NonNull
    @Override
    public com.example.kursovai_final3.ui.igroki.DescIgrokAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemDescIgrokBinding
                .inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new com.example.kursovai_final3.ui.igroki.DescIgrokAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.kursovai_final3.ui.igroki.DescIgrokAdapter.ViewHolder holder, int position) {
        holder.onBind(listD.get(position));
    }

    @Override
    public int getItemCount() {
        return listD.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull ItemDescIgrokBinding itemView) {
            super(itemView.getRoot());

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
        }
    }
}
