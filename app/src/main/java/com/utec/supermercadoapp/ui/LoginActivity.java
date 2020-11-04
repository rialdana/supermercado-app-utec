package com.utec.supermercadoapp.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.utec.supermercadoapp.R;
import com.utec.supermercadoapp.database.SupermarketDao;
import com.utec.supermercadoapp.database.SupermarketRoomDatabase;
import com.utec.supermercadoapp.database.entities.User;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private SupermarketDao dao;
    private MaterialButton mLoginButton;
    private TextInputLayout mTextUsername;
    private TextInputLayout mTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_SupermercadoApp);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViews();
        createDatabase();
        insertUsers();

        mLoginButton.setOnClickListener(v -> {
            verifyInputs();
        });
    }

    private void verifyInputs() {

        String username = Objects.requireNonNull(mTextUsername.getEditText()).getText().toString();
        String password = Objects.requireNonNull(mTextPassword.getEditText()).getText().toString();

        if (username.isEmpty() || password.isEmpty()) {
            showSnackbar("Por favor completa todos los campos");
        } else {
            loginUser(username, password);
        }
    }

    private void findViews() {
        mLoginButton = findViewById(R.id.button_login);
        mTextUsername = findViewById(R.id.text_input_layout_username);
        mTextPassword = findViewById(R.id.text_input_layout_password);
    }

    private void createDatabase() {
        SupermarketRoomDatabase db = SupermarketRoomDatabase.getDatabase(getApplicationContext());
        dao = db.supermarketDao();
    }

    private void insertUsers() {

        SupermarketRoomDatabase.databaseWriteExecutor.execute(() -> {

            User adminUser = new User("ricar", "123456", User.ADMIN);
            User regularUser1 = new User("juan", "123456", User.REGULAR);
            User regularUser2 = new User("jesus", "123456", User.REGULAR);
            User regularUser3 = new User("alex", "123456", User.REGULAR);

            dao.insertUser(adminUser);
            dao.insertUser(regularUser1);
            dao.insertUser(regularUser2);
            dao.insertUser(regularUser3);

        });
    }

    private void loginUser(String username, String password) {
        SupermarketRoomDatabase.databaseWriteExecutor.execute(() -> {

            User user = dao.verifyUser(username, password);

            if (user == null) {
                showSnackbar("Usuario no encontrado");
            } else {
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showSnackbar(String message) {
        Snackbar.make(mLoginButton, message, Snackbar.LENGTH_LONG).show();
    }
}