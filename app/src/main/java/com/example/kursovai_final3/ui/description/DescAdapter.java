package com.example.kursovai_final3.ui.description;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.MediaController;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kursovai_final3.databinding.ItemScoreDescCardBinding;
import com.example.kursovai_final3.ui.models.ScoreModel;

import java.util.ArrayList;
import java.util.List;

public class DescAdapter extends RecyclerView.Adapter<DescAdapter.ViewHolder> {

    ItemScoreDescCardBinding binding;

    List<ScoreModel> listD = new ArrayList<>();
    public void setListD(List<ScoreModel> listD) {
        this.listD = listD;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemScoreDescCardBinding
                .inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(listD.get(position));
    }

    @Override
    public int getItemCount() {
        return listD.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull ItemScoreDescCardBinding itemView) {
            super(itemView.getRoot());

        }

        public void onBind(ScoreModel scoreModel) {
            binding.imageTeamLeft.setImageResource(scoreModel.getLeftTeamImageResId());
            binding.imageTeamRight.setImageResource(scoreModel.getRightTeamImageResId());
            binding.textScore.setText(scoreModel.getScore());
            binding.textTeamLeftScorers.setText(scoreModel.getDescription());
            binding.textTeamRightScorers.setText(scoreModel.getExtraInfo());

            // Настройка VideoView для воспроизведения локального видео

            Context context = binding.videoView.getContext();
            int resId = context.getResources().getIdentifier(scoreModel.getMatchResId(), "raw", context.getPackageName());
            String videoPath = "android.resource://" + context.getPackageName() + "/" + resId;
            binding.videoView.setVideoURI(Uri.parse(videoPath));

            MediaController mediaController = new MediaController(itemView.getContext());
            mediaController.setAnchorView(binding.videoView);
            binding.videoView.setMediaController(mediaController);
            binding.videoView.start();
        }
    }
}
