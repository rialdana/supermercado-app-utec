package com.utec.supermercadoapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

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

public class HomeActivity extends AppCompatActivity implements categoriasListener, sucursalesListener {

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
        findViews(R.id.button_add_address);


       Button openFragmentButton = findViewById(R.id.button_add_categories);
       Button openFramentSucursales = findViewById(R.id.button_add_address);
       openFragmentButton.setOnClickListener(v -> {
            /**
             * Creando el fragment y mostrandolo, esta activity debe implementar la interfaz
             * OnTestCreateSomethingListener, si no la implementa la app podria crashear
             * porque es necesaria en el fragment para poderse comunicar de vuelta
             */
           TestCreateSomethingFragment fragment = TestCreateSomethingFragment.newInstance();
           fragment.show(getSupportFragmentManager(), fragment.getTag());
       });


        openFramentSucursales.setOnClickListener(v -> {
            /**
             * Creando el fragment y mostrandolo, esta activity debe implementar la interfaz
             * OnTestCreateSomethingListener, si no la implementa la app podria crashear
             * porque es necesaria en el fragment para poderse comunicar de vuelta
             */
            TestCreateSomethingFragment fragment = TestCreateSomethingFragment.newInstance();
            fragment.show(getSupportFragmentManager(), fragment.getTag());
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

            Log.i("TAG", "get_Lista_Categoria: "+ categoriasDao.Todo().get(0).getCategoria() );
            Log.i("TAG", "get_Lista_Categoria: "+ sucurslesDao.Todo().get(0).getNombre());

            RecyclerViewSucursales.setAdapter(new AdpatadorSucursales(sucurslesDao.Todo(), this));


        });
    }


    private void findViews(int button_add_address) {
        RecyclerViewCategorias = findViewById(R.id.Lista_Categorias);
        RecyclerViewSucursales = findViewById(R.id.Lista_Sucursales);
    }


    @Override
    public void selectCategorias(Categorias categoria) {

    }

    @Override
    public void selectSucursales(Sucursales surcursal) {

    }
}