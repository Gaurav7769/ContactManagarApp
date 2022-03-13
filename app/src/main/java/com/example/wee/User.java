package com.example.wee;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
    public class User {

        @PrimaryKey()
        @ColumnInfo(name = "username")
        @NonNull
        String username;

        @ColumnInfo(name = "password")
        String password;
    }

