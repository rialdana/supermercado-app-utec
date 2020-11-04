package com.utec.supermercadoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_SupermercadoApp);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}