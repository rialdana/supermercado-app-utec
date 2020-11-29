package com.utec.supermercadoapp.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.utec.supermercadoapp.R;
import com.utec.supermercadoapp.listeners.OnTestCreateSomethingListener;
import com.utec.supermercadoapp.listeners.SucursalesFragmentListener;

/**
 * Fragment creado para mostrar una parte de la pantalla con el contenido nada mas
 */
public class SucursalesFragment extends BottomSheetDialogFragment {

    /**
     * Hay que definir un listener, este listener es la interfaz mediante la cual nos vamos
     * a comunicar con la activity que contiene este fragment
     */
    private SucursalesFragmentListener listener;

    private Button mButtonCreateSomething;

    private SucursalesFragment() {
    }

    public static SucursalesFragment newInstance() {
        return new SucursalesFragment();
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
        return inflater.inflate(R.layout.fragment_sucursales, container, false);
    }

    /**
     * Con la vista creada podemos hacer los respectivos findViewById() o click listeners
     * o lo que sea que necesitemos
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }
}