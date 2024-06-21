package com.example.kursovai_final3;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Insert
    void insertUser(UserEntity user);

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    UserEntity getUserByEmailAndPassword(String email, String password);
}