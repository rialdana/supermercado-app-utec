package com.utec.supermercadoapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.utec.supermercadoapp.Adpater.AdaptadorProductos;
import com.utec.supermercadoapp.Adpater.productosListener;
import com.utec.supermercadoapp.R;
import com.utec.supermercadoapp.database.SupermarketRoomDatabase;
import com.utec.supermercadoapp.database.dao.ProductosDao;
import com.utec.supermercadoapp.database.entities.Categorias;
import com.utec.supermercadoapp.database.entities.Productos;
import com.utec.supermercadoapp.database.entities.User;
import com.utec.supermercadoapp.listeners.ProductosFragmentListener;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class ProductosActivity extends AppCompatActivity implements productosListener, ProductosFragmentListener {

    private String nomSucursal;
    private int idSucursal;
    private int userType;
    private TextView titulo;
    private ProductosDao productosDao;
    private RecyclerView RecyclerViewProductos;
    private Button openFragmentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        findViews();
        loadDatabase();
        getProductos();
        loadArguments();


        openFragmentButton.setOnClickListener(v -> {
            Fragment_Producto fragment_producto = Fragment_Producto.newInstance();
            fragment_producto.idSucursal = idSucursal;
            fragment_producto.show(getSupportFragmentManager(), fragment_producto.getTag());
        });
    }

    private void loadDatabase() {
        SupermarketRoomDatabase db = SupermarketRoomDatabase.getDatabase(getApplicationContext());
        productosDao = db.productosDao();
    }

    private void loadArguments() {
        nomSucursal = getIntent().getStringExtra("nomSucursal");
        idSucursal = getIntent().getExtras().getInt("idSucursal");
        userType = getIntent().getIntExtra(User.USER_TYPE_TAG, User.REGULAR);

        if (userType == User.REGULAR) {
            openFragmentButton.setVisibility(View.GONE);
        }

        titulo.setText("Sucursal " + nomSucursal);
    }


    private void findViews() {
        titulo = findViewById(R.id.textView_sucursal);
        RecyclerViewProductos = findViewById(R.id.Lista_Productos);
        openFragmentButton = findViewById(R.id.button_add_producto);
    }

    public void getProductos() {
        SupermarketRoomDatabase.databaseWriteExecutor.execute(() -> {
            RecyclerViewProductos.setAdapter(new AdaptadorProductos(productosDao.Uno(idSucursal), this));
        });
    }

    @Override
    public void selectProductos(Productos producto) {

    }

    @Override
    public void InsertarProducto(Productos producto) {
        SupermarketRoomDatabase.databaseWriteExecutor.execute(() -> {
            productosDao.Insertar(producto);
        });

        getProductos();
    }

    private void showSnackbar(String message) {
        Snackbar.make(openFragmentButton, message, Snackbar.LENGTH_LONG).show();
    }
}