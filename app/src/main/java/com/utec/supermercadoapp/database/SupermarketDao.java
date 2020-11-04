package com.utec.supermercadoapp.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.utec.supermercadoapp.database.entities.User;

@Dao
public interface SupermarketDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertUser(User user);

    @Query("SELECT * FROM user_table WHERE username LIKE :name AND password LIKE :password LIMIT 1")
    User verifyUser(String name, String password);
}
