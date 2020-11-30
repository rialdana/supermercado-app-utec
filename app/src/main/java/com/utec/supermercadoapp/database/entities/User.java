package com.utec.supermercadoapp.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey
    @NonNull
    private String username;

    @NonNull
    private String password;

    @NonNull
    private int userType;

    public static final int ADMIN = 0;
    public static final int REGULAR = 1;
    public static final String USER_TYPE_TAG = "user_type";

    public User(@NonNull String username, @NonNull String password, @NonNull int userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getUserType() {
        return userType;
    }

}
