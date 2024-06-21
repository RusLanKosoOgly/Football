package com.example.kursovai_final3.ui.sobytia;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kursovai_final3.R;
import com.example.kursovai_final3.ui.models.Sob;

import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {


    private final List<Sob> sobList;
    private final OnItemClickListener listener;
    private SobDao sobDao; // DAO для работы с базой данных


    public interface OnItemClickListener {
        void onItemClick(Sob sob);
    }

    public EventsAdapter(List<Sob> sobList, OnItemClickListener listener, SobDao sobDao) {
        this.sobList = sobList;
        this.listener = listener;
        this.sobDao = sobDao;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sob, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Sob sob = sobList.get(position);
        holder.name.setText(sob.getName());
        holder.desc.setText(sob.getDescription());
        holder.image.setImageBitmap(BitmapFactory.decodeByteArray(sob.getImage(), 0, sob.getImage().length));




        // Установка обработчика клика на кнопку "Delete"
        holder.deleteButton.setOnClickListener(v -> {
            // Удаление элемента из базы данных
            sobDao.deleteSob(sob);

            // Удаление элемента из списка данных адаптера
            sobList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, sobList.size());
        });
    }


    @Override
    public int getItemCount() {
        return sobList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView desc;
        ImageView image;

        Button deleteButton;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_card);
            image = itemView.findViewById(R.id.image_card);
            desc = itemView.findViewById(R.id.description_card);

            deleteButton = itemView.findViewById(R.id.delete); // Найти кнопку "Delete"
        }
    }
}
