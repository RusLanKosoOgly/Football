package com.example.kursovai_final3.ui.match;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kursovai_final3.R;
import com.example.kursovai_final3.databinding.ItemMatchiBinding;
import com.example.kursovai_final3.ui.models.MatchModel;

import java.util.ArrayList;
import java.util.List;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder> {
    private List<MatchModel> list_m = new ArrayList<>();
    private ArrayList<MatchModel> selected_list = new ArrayList<>();
    private NavController navController;

    public void setList_m(List<MatchModel> list_m) {
        this.list_m = list_m;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMatchiBinding binding = ItemMatchiBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
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
        private ItemMatchiBinding binding;

        public ViewHolder(@NonNull ItemMatchiBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
            Button btnAddTicket = itemView.btnAddTicket;
            TextView textTicketCount = itemView.textTicketCount;
            TextView textTotalPrice = itemView.textTotalPrice;
            Button btnPay = itemView.btnPay;

            // Настройка обработчика нажатия кнопки "Добавить билет"
            btnAddTicket.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Извлечение только числовой части из текста
                    String text = textTicketCount.getText().toString();
                    int ticketCount = Integer.parseInt(text.replaceAll("[^0-9]", ""));
                    ticketCount++;
                    textTicketCount.setText("Количество билетов: " + ticketCount);

                    // Вычисляем итоговую цену и обновляем текстовое поле
                    int ticketPrice = 1000; // Ваша цена билета
                    int totalPrice = ticketCount * ticketPrice;
                    textTotalPrice.setText("Итоговая цена: " + totalPrice + " руб.");
                }
            });

            // Настройка обработчика нажатия кнопки "Оплатить"
            btnPay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        public void onBind(MatchModel matchModel) {
            binding.imageTeamLeft.setImageResource(matchModel.getLeftTeamImageResId());
            binding.imageTeamRight.setImageResource(matchModel.getRightTeamImageResId());
            binding.textEventName.setText(matchModel.getTitle());
            binding.textEventDescription.setText(matchModel.getDescription());
            binding.textEventPrice.setText(matchModel.getPrice());

            binding.btnParticipate.setOnClickListener(view -> {
                selected_list.add(matchModel);
                navController = Navigation.findNavController((Activity) itemView.getContext(), R.id.nav_host_fragment_activity_main);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("favorite", selected_list);
                navController.navigate(R.id.DescMatchFragment, bundle);
            });
        }
    }
}
