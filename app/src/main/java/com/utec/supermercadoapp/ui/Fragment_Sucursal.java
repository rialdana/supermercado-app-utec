package com.utec.supermercadoapp.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputLayout;
import com.utec.supermercadoapp.R;
import com.utec.supermercadoapp.database.entities.Categorias;
import com.utec.supermercadoapp.database.entities.Sucursales;
import com.utec.supermercadoapp.listeners.SucursalesFragmentListener;


import java.util.Objects;

public class Fragment_Sucursal extends BottomSheetDialogFragment {

    private TextInputLayout Nombre, Direccion, Telefono;

    /**
     * Hay que definir un listener, este listener es la interfaz mediante la cual nos vamos
     * a comunicar con la activity que contiene este fragment
     */
    private SucursalesFragmentListener listener;

    //private Button mButtonCreateSomething;

    private Fragment_Sucursal() {
    }

    public static Fragment_Sucursal newInstance() {
        return new Fragment_Sucursal();
    }

    /**
     * Se castea el contexto hacia la interfaz, y lo asignamos a nuestro listener.
     *
     * @param context
     */
    @Override
    public void onAttach(@NonNull Context context) {
        try {
            listener = (SucursalesFragmentListener) context;
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
        return inflater.inflate(R.layout.fragment__sucursal, container, false);
    }

    /**
     * Con la vista creada podemos hacer los respectivos findViewById() o click listeners
     * o lo que sea que necesitemos
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Nombre = view.findViewById(R.id.text_input_layout_nombre_sucursal);
        Direccion = view.findViewById(R.id.text_input_layout_direccion_sucursal);
        Telefono = view.findViewById(R.id.text_input_layout_telefono_sucursal);


        view.findViewById(R.id.button_guardar_sucursal).setOnClickListener(v -> {

            String nombre = Objects.requireNonNull(Nombre.getEditText()).getText().toString();
            String direccion =  Objects.requireNonNull(Direccion.getEditText()).getText().toString();
            String telefono =  Objects.requireNonNull(Telefono.getEditText()).getText().toString();


            Sucursales sucursal = new Sucursales(nombre, direccion, telefono);

            /**
             * Para comunicarnos con la activity, basta con llamar un metodo del listener y esto
             * automaticamente ejecutará el metodo que se encuentra implementado
             * en la activity que contiene este fragment
             */

            listener.InsetarSucursal(sucursal);
            dismiss();
        });
    }
}