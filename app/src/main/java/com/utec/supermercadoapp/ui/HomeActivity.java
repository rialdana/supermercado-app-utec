package com.utec.supermercadoapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
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
import com.utec.supermercadoapp.listeners.CategoriasFragmentListener;
import com.utec.supermercadoapp.listeners.SucursalesFragmentListener;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity implements categoriasListener, sucursalesListener, CategoriasFragmentListener, SucursalesFragmentListener {

    private CategoriasDao categoriasDao;
    private SucurslesDao sucurslesDao;
    private RecyclerView RecyclerViewCategorias, RecyclerViewSucursales;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_SupermercadoApp);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        createDatabase();
        get_Lista_Categoria();
        findViews();



        Button openFragmentButton = findViewById(R.id.button_add_categories);
        Button openFramentSucursales = findViewById(R.id.button_add_address);





       openFragmentButton.setOnClickListener(v -> {
            /**
             * Creando el fragment y mostrandolo, esta activity debe implementar la interfaz
             * OnTestCreateSomethingListener, si no la implementa la app podria crashear
             * porque es necesaria en el fragment para poderse comunicar de vuelta
             */

           //TestCreateSomethingFragment fragment = TestCreateSomethingFragment.newInstance();
          // fragment.show(getSupportFragmentManager(), fragment.getTag());

           Fragment_Categoria fragment_categoria = Fragment_Categoria.newInstance();
           fragment_categoria.show(getSupportFragmentManager(), fragment_categoria.getTag());

       });


        openFramentSucursales.setOnClickListener(v -> {
            /**
             * Creando el fragment y mostrandolo, esta activity debe implementar la interfaz
             * OnTestCreateSomethingListener, si no la implementa la app podria crashear
             * porque es necesaria en el fragment para poderse comunicar de vuelta
             */


            Fragment_Sucursal fragment_sucursal = Fragment_Sucursal.newInstance();
            fragment_sucursal.show(getSupportFragmentManager(), fragment_sucursal.getTag());

        });


    }


    private void createDatabase() {
        SupermarketRoomDatabase db = SupermarketRoomDatabase.getDatabase(getApplicationContext());
        categoriasDao = db.categoriasDao();
        sucurslesDao = db.sucurslesDao();
    }

    public void  get_Lista_Categoria(){
        SupermarketRoomDatabase.databaseWriteExecutor.execute(() -> {
            RecyclerViewCategorias.setAdapter(new AdpatadorCategorias(categoriasDao.Todo(), this));



            RecyclerViewSucursales.setAdapter(new AdpatadorSucursales(sucurslesDao.Todo(), this));
        });
    }


    private void findViews() {
        RecyclerViewCategorias = findViewById(R.id.Lista_Categorias);
        RecyclerViewSucursales = findViewById(R.id.Lista_Sucursales);
    }



    @Override
    public void selectCategorias(Categorias categoria) {

    }

    @Override
    public void selectSucursales(Sucursales surcursal) {

    }


    @Override
    public void InsetarCategoria(Categorias categorias) {
        SupermarketRoomDatabase.databaseWriteExecutor.execute(() -> {
            categoriasDao.Insertar(categorias);
        });
    }

    @Override
    public void InsetarSucursal(Sucursales sucursales) {
        SupermarketRoomDatabase.databaseWriteExecutor.execute(() -> {

            sucurslesDao.Insertar(sucursales);
            
        });
    }
}