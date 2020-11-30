package com.utec.supermercadoapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.utec.supermercadoapp.R;
import com.utec.supermercadoapp.database.SupermarketDao;
import com.utec.supermercadoapp.database.SupermarketRoomDatabase;
import com.utec.supermercadoapp.database.dao.CategoriasDao;
import com.utec.supermercadoapp.database.dao.ProductosDao;
import com.utec.supermercadoapp.database.dao.SucurslesDao;
import com.utec.supermercadoapp.database.entities.Categorias;
import com.utec.supermercadoapp.database.entities.Productos;
import com.utec.supermercadoapp.database.entities.Sucursales;
import com.utec.supermercadoapp.database.entities.User;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private SupermarketDao dao;
    private CategoriasDao categoriasDao;
    private SucurslesDao sucurslesDao;
    private ProductosDao productosDao;
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
        categoriasDao = db.categoriasDao();
        sucurslesDao = db.sucurslesDao();
        productosDao=db.productosDao();
    }

    private void insertUsers() {

        SupermarketRoomDatabase.databaseWriteExecutor.execute(() -> {

            User adminUser = new User("ricar", "123456", User.ADMIN);
            User regularUser1 = new User("juan", "123456", User.ADMIN);
            User regularUser2 = new User("jesus", "123456", User.REGULAR);
            User regularUser3 = new User("alex", "123456", User.REGULAR);

            Categorias categoria1 = new Categorias("Limpieza");
            Categorias categoria2 = new Categorias("Carnes");
            Categorias categoria3 = new Categorias("Macotas");
            Categorias categoria4 = new Categorias("Granos Basicos");

            Sucursales sucursal1 = new Sucursales("San Salvador", "1a Calle Poniente. Y 1a Avenida Norte No. 216" ,"2222-5555");
            Sucursales sucursal2 = new Sucursales("Apopa", "Km. 12 Carretera Troncal del Norte" ,"2222-5555");

            dao.insertUser(adminUser);
            dao.insertUser(regularUser1);
            dao.insertUser(regularUser2);
            dao.insertUser(regularUser3);

            if(categoriasDao.Uno(1) == null) {
                categoriasDao.Insertar(categoria1);
                categoriasDao.Insertar(categoria2);
                categoriasDao.Insertar(categoria3);
                categoriasDao.Insertar(categoria4);
                sucurslesDao.Insertar(sucursal1);
                sucurslesDao.Insertar(sucursal2);
            }

            Productos productos= new Productos("leche","botella sdsf",5,1.50,1,1);
            productosDao.Insertar(productos);









            Log.i("TAG", "get_Lista_Categoria: "+ categoriasDao.Todo().get(0).getCategoria() );

        });

    }

    private void loginUser(String username, String password) {
        SupermarketRoomDatabase.databaseWriteExecutor.execute(() -> {

            User user = dao.verifyUser(username, password);

            if (user == null) {
                showSnackbar("Usuario no encontrado");
            } else if(user.getUserType() == user.ADMIN) {
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
            } else {
                // intent para usuario regular
            }
        });
    }

    private void showSnackbar(String message) {
        Snackbar.make(mLoginButton, message, Snackbar.LENGTH_LONG).show();
    }
}