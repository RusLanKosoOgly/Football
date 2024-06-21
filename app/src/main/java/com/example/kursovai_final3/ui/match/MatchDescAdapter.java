package com.example.kursovai_final3.ui.match;

import android.app.Activity;
import android.content.Context;
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
import com.example.kursovai_final3.databinding.ItemDescmatchBinding;
import com.example.kursovai_final3.ui.models.MatchModel;

import java.util.ArrayList;
import java.util.List;

public class MatchDescAdapter extends RecyclerView.Adapter<MatchDescAdapter.ViewHolder> {

    private List<MatchModel> listD = new ArrayList<>();
    private NavController navController;

    public void setListD(List<MatchModel> listD) {
        this.listD = listD;
        notifyDataSetChanged(); // Уведомляем адаптер о изменении данных
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDescmatchBinding binding = ItemDescmatchBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
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
        private final ItemDescmatchBinding binding;

        public ViewHolder(@NonNull ItemDescmatchBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            Button btnAddTicket = binding.btnAddTicket;
            TextView textTicketCount = binding.textTicketCount;
            TextView textTotalPrice = binding.textTotalPrice;
            Button btnPay = binding.btnPay;

            btnAddTicket.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text = textTicketCount.getText().toString();
                    int ticketCount = Integer.parseInt(text.replaceAll("[^0-9]", ""));
                    ticketCount++;
                    textTicketCount.setText("Количество билетов: " + ticketCount);

                    int ticketPrice = 1000; // Ваша цена билета
                    int totalPrice = ticketCount * ticketPrice;
                    textTotalPrice.setText("Итоговая цена: " + totalPrice + " руб.");
                }
            });

            btnPay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    navController = Navigation.findNavController((Activity) context, R.id.nav_host_fragment_activity_main);
                    navController.navigate(R.id.action_DescMatchFragment_to_PaymentFragment);
                }
            });
        }

        public void onBind(MatchModel matchModel) {
            binding.imageTeamLeft.setImageResource(matchModel.getLeftTeamImageResId());
            binding.imageTeamRight.setImageResource(matchModel.getRightTeamImageResId());
            binding.textEventName.setText(matchModel.getTitle());
            binding.textEventDescription.setText(matchModel.getDescription());
            binding.textEventPrice.setText(matchModel.getPrice());
        }
    }
}
