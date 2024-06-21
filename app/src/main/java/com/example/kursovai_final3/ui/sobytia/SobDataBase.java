package com.example.kursovai_final3.ui.sobytia;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.kursovai_final3.ui.models.Sob;

@Database(entities = {Sob.class}, version = 1)
public abstract class SobDataBase extends RoomDatabase {
    public abstract SobDao sobDao();
}
