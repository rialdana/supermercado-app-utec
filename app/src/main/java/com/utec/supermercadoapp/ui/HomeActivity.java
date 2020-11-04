package com.utec.supermercadoapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.utec.supermercadoapp.R;
import com.utec.supermercadoapp.listeners.OnTestCreateSomethingListener;

public class HomeActivity extends AppCompatActivity implements OnTestCreateSomethingListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_SupermercadoApp);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        MaterialButton openFragmentButton = findViewById(R.id.button_open_test_fragment);

        openFragmentButton.setOnClickListener(v -> {

            /**
             * Creando el fragment y mostrandolo, esta activity debe implementar la interfaz
             * OnTestCreateSomethingListener, si no la implementa la app podria crashear
             * porque es necesaria en el fragment para poderse comunicar de vuelta
             */
            TestCreateSomethingFragment fragment = TestCreateSomethingFragment.newInstance();
            fragment.show(getSupportFragmentManager(), fragment.getTag());
        });
    }

    /**
     * Metodo utilizado por el fragment para comunicarse con la activity, este metodo se puede
     * modificar para retornar cualquier tipo de dato mediante sus parametros, asi podemos
     * utilizarlos, guardarlos en la BD, o hacer cualquier operacion en la activity
     */
    @Override
    public void onCreateSomething(String firstParameter, int secondParameter) {
        Toast.makeText(getApplicationContext(), firstParameter + " " + secondParameter, Toast.LENGTH_LONG).show();
    }
}