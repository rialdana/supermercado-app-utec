package com.utec.supermercadoapp.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.utec.supermercadoapp.R;
import com.utec.supermercadoapp.database.SupermarketDao;
import com.utec.supermercadoapp.database.SupermarketRoomDatabase;
import com.utec.supermercadoapp.database.dao.CategoriasDao;
import com.utec.supermercadoapp.database.dao.SucurslesDao;
import com.utec.supermercadoapp.database.entities.Categorias;
import com.utec.supermercadoapp.listeners.CategoriasFragmentListener;

import java.util.Objects;


/**
 * Fragment creado para mostrar una parte de la pantalla con el contenido nada mas
 */
public class CategoriasFragment extends BottomSheetDialogFragment {

    /**
     * Hay que definir un listener, este listener es la interfaz mediante la cual nos vamos
     * a comunicar con la activity que contiene este fragment
     */
    private CategoriasFragmentListener listener;


    private TextInputLayout Nombre;

    private CategoriasFragment() {
    }

    public static CategoriasFragment newInstance() {
        return new CategoriasFragment();
    }

    /**
     * Se castea el contexto hacia la interfaz, y lo asignamos a nuestro listener.
     *
     * @param context
     */
    @Override
    public void onAttach(@NonNull Context context) {
        try {
            listener = (CategoriasFragmentListener) context;
        } catch (Exception e) {
            listener = null;
        }
        super.onAttach(context);
    }

    /**
     * Se elimina la referencia al listener
     */
    @Override
    public void onDetach() {
        listener = null;
        super.onDetach();
    }

    /**
     * Se infla la vista
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_categorias, container, false);
    }

    /**
     * Con la vista creada podemos hacer los respectivos findViewById() o click listeners
     * o lo que sea que necesitemos
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Nombre = view.findViewById(R.id.text_input_layout_nombre_categoria);
        String nombre = Objects.requireNonNull(Nombre.getEditText()).getText().toString();
        Categorias categoria = new Categorias(nombre);

       view.findViewById(R.id.guardar_categoria).setOnClickListener(v -> {

            /**
             * Para comunicarnos con la activity, basta con llamar un metodo del listener y esto
             * automaticamente ejecutará el metodo que se encuentra implementado
             * en la activity que contiene este fragment
             */
            listener.onCreateSomething(categoria);
            dismiss();
        });
    }
}