package com.example.kursovai_final3.ui.sobytia;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.kursovai_final3.R;
import com.example.kursovai_final3.ui.models.Sob;

import java.io.Serializable;

public class SobytiaDescFragment extends Fragment {
    private static final String ARG_SOB = "sob";

    private Sob sob;

    public static SobytiaDescFragment newInstance(Sob sob) {
        SobytiaDescFragment fragment = new SobytiaDescFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_SOB, (Serializable) sob);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            sob = (Sob) getArguments().getSerializable(ARG_SOB);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sobytiadesc, container, false);

        ImageView imageView = view.findViewById(R.id.image);
        TextView nameTextView = view.findViewById(R.id.name);
        TextView descriptionTextView = view.findViewById(R.id.description);

        // Set data to views
        nameTextView.setText(sob.getName());
        descriptionTextView.setText(sob.getDescription());
        imageView.setImageBitmap(BitmapFactory.decodeByteArray(sob.getImage(), 0, sob.getImage().length));

        return view;
    }
}
