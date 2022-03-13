package com.example.wee;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
@Dao
public interface OperationDao {

        @Query("SELECT * FROM Contact")
        List<Contact> getAll();

        @Query("SELECT EXISTS( SELECT * FROM User WHERE username =:userName AND password =:password)")
        Boolean getUser(String userName,String password);

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        Long insert(User task);

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        Long insert(Contact task);



}

