package com.example.kursovai_final3.ui.sobytia;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.kursovai_final3.ui.models.Sob;

import java.util.List;

@Dao
public interface SobDao {
    @Insert
    void insert(Sob sob);

    @Delete
    void deleteSob(Sob sob);

    @Query("SELECT * FROM sob_table")
    List<Sob> getAllSobs();
}
