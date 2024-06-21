package com.example.kursovai_final3;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kursovai_final3.databinding.ItemResultatBinding;
import com.example.kursovai_final3.ui.models.ScoreModel;

import java.util.ArrayList;
import java.util.List;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ViewHolder> {
    ItemResultatBinding binding;
    List<ScoreModel> list_m = new ArrayList<>();
    private ArrayList<ScoreModel> selected_list = new ArrayList<>();
    NavController navController;

    public void setList_m(List<ScoreModel> list_m) {
        this.list_m = list_m;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemResultatBinding
                .inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list_m.get(position));
    }

    @Override
    public int getItemCount() {
        return list_m.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemResultatBinding binding;

        public ViewHolder(@NonNull ItemResultatBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void onBind(ScoreModel scoreModel) {
            binding.imageTeamLeft.setImageResource(scoreModel.getLeftTeamImageResId());
            binding.imageTeamRight.setImageResource(scoreModel.getRightTeamImageResId());
            binding.textScore.setText(scoreModel.getScore());
            binding.textTeamLeftScorers.setText(scoreModel.getDescription());
            binding.textTeamRightScorers.setText(scoreModel.getExtraInfo());

            Context context = binding.videoView.getContext();
            int resId = context.getResources().getIdentifier(scoreModel.getMatchResId(), "raw", context.getPackageName());
            String videoPath = "android.resource://" + context.getPackageName() + "/" + resId;
            binding.videoView.setVideoURI(Uri.parse(videoPath));



            binding.btnDone.setOnClickListener(view -> {
                selected_list.add(scoreModel);
                navController = Navigation.findNavController((Activity) itemView.getContext(), R.id.nav_host_fragment_activity_main);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("favorite", selected_list);
                navController.navigate(R.id.descFragment, bundle);
            });
        }
    }
}
