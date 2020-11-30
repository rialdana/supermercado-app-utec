package com.utec.supermercadoapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.utec.supermercadoapp.Adpater.AdaptadorProductos;
import com.utec.supermercadoapp.Adpater.productosListener;
import com.utec.supermercadoapp.R;
import com.utec.supermercadoapp.database.SupermarketRoomDatabase;
import com.utec.supermercadoapp.database.dao.ProductosDao;
import com.utec.supermercadoapp.database.entities.Categorias;
import com.utec.supermercadoapp.database.entities.Productos;
import com.utec.supermercadoapp.listeners.ProductosFragmentListener;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class ProductosActivity extends AppCompatActivity implements productosListener, ProductosFragmentListener {
private String nomSucursal;
private int idSucursal;
private TextView titulo;
private ProductosDao productosDao;
private RecyclerView RecyclerViewProductos;
private Button openFragmentButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        nomSucursal= getIntent().getStringExtra("nomSucursal");
        idSucursal=getIntent().getExtras().getInt("idSucursal");


        findViews();
        getProductos();
        titulo.setText("Sucursal "+nomSucursal);



        openFragmentButton.setOnClickListener(v -> {
            /**
             * Creando el fragment y mostrandolo, esta activity debe implementar la interfaz
             * OnTestCreateSomethingListener, si no la implementa la app podria crashear
             * porque es necesaria en el fragment para poderse comunicar de vuelta
             */

            //TestCreateSomethingFragment fragment = TestCreateSomethingFragment.newInstance();
            // fragment.show(getSupportFragmentManager(), fragment.getTag());

            Fragment_Producto fragment_producto = Fragment_Producto.newInstance();
            fragment_producto.idSucursal=idSucursal;
            fragment_producto.show(getSupportFragmentManager(), fragment_producto.getTag());

        });
    }


    private void findViews() {
        titulo=findViewById(R.id.textView_sucursal);
        RecyclerViewProductos=findViewById(R.id.Lista_Productos);
        openFragmentButton = findViewById(R.id.button_add_producto);
    }

    public void  getProductos(){
        SupermarketRoomDatabase db = SupermarketRoomDatabase.getDatabase(getApplicationContext());
        productosDao=db.productosDao();
        SupermarketRoomDatabase.databaseWriteExecutor.execute(() -> {
            RecyclerViewProductos.setAdapter(new AdaptadorProductos(productosDao.Uno(idSucursal), this));
        });
    }

    @Override
    public void selectProductos(Productos producto) {

    }
    @Override
    public void InsertarProducto(Productos producto) {
        SupermarketRoomDatabase db = SupermarketRoomDatabase.getDatabase(getApplicationContext());
        db.databaseWriteExecutor.execute(() -> {
            productosDao.Insertar(producto);
        });
        //getProductos();
    }

}