package com.example.kursovai_final3.ui.models;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sob_table")
public class Sob {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String description;
    private byte[] image;

    public Sob(String name, String description, byte[] image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public byte[] getImage() {
        return image;
    }
}
