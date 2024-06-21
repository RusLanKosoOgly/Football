package com.example.kursovai_final3.ui.sobytia;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kursovai_final3.R;
import com.example.kursovai_final3.ui.models.Sob;

import java.util.List;

public class EventsVseAdapter extends RecyclerView.Adapter<EventsVseAdapter.ViewHolder> {

    private final List<Sob> sobList;
    private final OnItemClickListener listener;
    private SobDao sobDao; // DAO для работы с базой данных

    public interface OnItemClickListener {
        void onItemClick(Sob sob);
    }

    public EventsVseAdapter(List<Sob> sobList, OnItemClickListener listener, SobDao sobDao) {
        this.sobList = sobList;
        this.listener = listener;
        this.sobDao = sobDao;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sobvse, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Sob sob = sobList.get(position);
        holder.name.setText(sob.getName());
        holder.desc.setText(sob.getDescription());
        holder.image.setImageBitmap(BitmapFactory.decodeByteArray(sob.getImage(), 0, sob.getImage().length));

        // Установка обработчика клика на кнопку "Подробнее"
        holder.buttonMore.setOnClickListener(v -> {
            openWhatsApp(holder.itemView, "+996555922325");
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
        Button buttonMore;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_card);
            image = itemView.findViewById(R.id.image_card);
            desc = itemView.findViewById(R.id.description_card);
            buttonMore = itemView.findViewById(R.id.button_more);
        }
    }

    private void openWhatsApp(View view, String phoneNumber) {
        try {
            String url = "https://wa.me/" + phoneNumber;
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            view.getContext().startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(view.getContext(), "WhatsApp is not installed on this device", Toast.LENGTH_SHORT).show();
        }
    }
}
