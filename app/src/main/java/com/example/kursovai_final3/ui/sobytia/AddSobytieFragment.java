package com.example.kursovai_final3.ui.sobytia;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.room.Room;

import com.example.kursovai_final3.MainActivity;
import com.example.kursovai_final3.R;
import com.example.kursovai_final3.databinding.FragmentAddsobytieBinding;
import com.example.kursovai_final3.ui.models.Sob;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddSobytieFragment extends Fragment {
    NavController navController;

    private FragmentAddsobytieBinding binding;
    private SobDataBase sobDataBase;
    private SobDao sobDao;
    private ActivityResultLauncher<String> contentLauncher;
    private Bitmap bitmapImageSob;
    private boolean isImgSelect = false;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddsobytieBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sobDataBase = Room.databaseBuilder(getContext(), SobDataBase.class, "database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        sobDao = sobDataBase.sobDao();

        binding.btnLoad.setOnClickListener(v1 -> contentLauncher.launch("image/*"));

        contentLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        try {
                            bitmapImageSob = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), result);
                            binding.kotik.setImageBitmap(bitmapImageSob);
                            isImgSelect = true;
                        } catch (IOException e) {
                            e.printStackTrace();
                            isImgSelect = false;
                        }
                    }
                });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnAdd.setOnClickListener(v2 -> {
            String nameSur = binding.nameSurForm.getText().toString();
            String num = binding.numForm.getText().toString();

            if (nameSur.isEmpty() || num.isEmpty()) {
                Toast.makeText(requireActivity(), "Заполните поле контакты", Toast.LENGTH_SHORT).show();
                isImgSelect = false;
            } else {
                if (isImgSelect) {
                    ByteArrayOutputStream baosImageStudent = new ByteArrayOutputStream();
                    bitmapImageSob.compress(Bitmap.CompressFormat.PNG, 100, baosImageStudent);
                    byte[] imgSob = baosImageStudent.toByteArray();

                    Sob sob = new Sob(nameSur, num, imgSob);
                    sobDao.insert(sob);

                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(requireActivity(), "Загрузите фото", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.btnBack.setOnClickListener(v -> {
            navController = Navigation.findNavController(requireActivity(),
                    R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.action_AddSobytieFragmnet_to_AdminFragment);
        });
    }
}
