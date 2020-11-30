package com.utec.supermercadoapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


import com.google.android.material.snackbar.Snackbar;
import com.utec.supermercadoapp.Adpater.AdpatadorCategorias;
import com.utec.supermercadoapp.Adpater.AdpatadorSucursales;
import com.utec.supermercadoapp.Adpater.categoriasListener;
import com.utec.supermercadoapp.Adpater.sucursalesListener;
import com.utec.supermercadoapp.R;
import com.utec.supermercadoapp.database.SupermarketRoomDatabase;
import com.utec.supermercadoapp.database.dao.CategoriasDao;
import com.utec.supermercadoapp.database.dao.SucurslesDao;
import com.utec.supermercadoapp.database.entities.Categorias;
import com.utec.supermercadoapp.database.entities.Sucursales;
import com.utec.supermercadoapp.database.entities.User;
import com.utec.supermercadoapp.listeners.CategoriasFragmentListener;
import com.utec.supermercadoapp.listeners.SucursalesFragmentListener;

import java.util.Collections;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements categoriasListener, sucursalesListener, CategoriasFragmentListener, SucursalesFragmentListener {

    private CategoriasDao categoriasDao;
    private SucurslesDao sucurslesDao;
    private RecyclerView RecyclerViewCategorias, RecyclerViewSucursales;
    private Button openFragmentButton, openFramentSucursales;
    private ImageButton goBackButton;
    private int userType;
    private AdpatadorCategorias adaptadorCategorias;
    private AdpatadorSucursales adaptadorSucursales;
    private List<Categorias> categoriasList = Collections.emptyList();
    private List<Sucursales> sucursalesList = Collections.emptyList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_SupermercadoApp);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViews();
        createDatabase();
        loadDatabaseInfo();
        validateUserType();
        setClickListeners();
    }

    private void setClickListeners() {
        goBackButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        openFragmentButton.setOnClickListener(v -> {
            Fragment_Categoria fragment_categoria = Fragment_Categoria.newInstance();
            fragment_categoria.show(getSupportFragmentManager(), fragment_categoria.getTag());
        });

        openFramentSucursales.setOnClickListener(v -> {
            Fragment_Sucursal fragment_sucursal = Fragment_Sucursal.newInstance();
            fragment_sucursal.show(getSupportFragmentManager(), fragment_sucursal.getTag());
        });
    }

    private void validateUserType() {
        userType = getIntent().getIntExtra(User.USER_TYPE_TAG, User.REGULAR);

        if (userType == User.REGULAR) {
            openFragmentButton.setVisibility(View.GONE);
            openFramentSucursales.setVisibility(View.GONE);
        }
    }

    private void createDatabase() {
        SupermarketRoomDatabase db = SupermarketRoomDatabase.getDatabase(getApplicationContext());
        categoriasDao = db.categoriasDao();
        sucurslesDao = db.sucurslesDao();
    }

    public void loadDatabaseInfo() {

        adaptadorCategorias = new AdpatadorCategorias(categoriasList, this);
        adaptadorSucursales = new AdpatadorSucursales(sucursalesList, this);

        RecyclerViewCategorias.setAdapter(adaptadorCategorias);
        RecyclerViewSucursales.setAdapter(adaptadorSucursales);

        SupermarketRoomDatabase.databaseWriteExecutor.execute(() -> {
            categoriasList = categoriasDao.Todo();
            sucursalesList = sucurslesDao.Todo();
            runOnUiThread(this::updateData);
        });
    }

    private void updateData() {
        adaptadorSucursales.setNewList(sucursalesList);
        adaptadorCategorias.setNewList(categoriasList);
    }

    private void findViews() {
        RecyclerViewCategorias = findViewById(R.id.Lista_Categorias);
        RecyclerViewSucursales = findViewById(R.id.Lista_Sucursales);
        openFragmentButton = findViewById(R.id.button_add_categories);
        openFramentSucursales = findViewById(R.id.button_add_address);
        goBackButton = findViewById(R.id.boton_Atras);
    }


    @Override
    public void selectCategorias(Categorias categoria) {

    }

    @Override
    public void selectSucursales(Sucursales sucursal) {
        Intent intent = new Intent(this, ProductosActivity.class);
        intent.putExtra("nomSucursal", sucursal.getNombre());
        intent.putExtra("idSucursal", sucursal.getId_sucursal());
        intent.putExtra(User.USER_TYPE_TAG, userType);
        startActivity(intent);
    }

    @Override
    public void InsetarCategoria(Categorias categorias) {
        SupermarketRoomDatabase.databaseWriteExecutor.execute(() -> {
            categoriasDao.Insertar(categorias);
            categoriasList = categoriasDao.Todo();
            runOnUiThread(this::updateData);
        });

        showSnackbar("Categoria guardada exitosamente!");
    }

    @Override
    public void InsetarSucursal(Sucursales sucursales) {
        SupermarketRoomDatabase.databaseWriteExecutor.execute(() -> {
            sucurslesDao.Insertar(sucursales);
            sucursalesList = sucurslesDao.Todo();
            runOnUiThread(this::updateData);
        });

        showSnackbar("Sucursal guardada exitosamente!");
    }

    private void showSnackbar(String message) {
        Snackbar.make(goBackButton, message, Snackbar.LENGTH_LONG).show();
    }
}