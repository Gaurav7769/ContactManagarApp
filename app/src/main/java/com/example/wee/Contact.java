package com.example.wee;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contact {

    @ColumnInfo(name = "name")
    String name;

    @ColumnInfo(name = "phoneNumber")
    @PrimaryKey()
    @NonNull
    String phoneNumber;

    @ColumnInfo(name = "email")
    String email;
}
