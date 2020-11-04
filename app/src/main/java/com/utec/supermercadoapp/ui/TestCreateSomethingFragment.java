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

/**
 * Fragment creado para mostrar una parte de la pantalla con el contenido nada mas
 */
public class TestCreateSomethingFragment extends BottomSheetDialogFragment {

    /**
     * Hay que definir un listener, este listener es la interfaz mediante la cual nos vamos
     * a comunicar con la activity que contiene este fragment
     */
    private OnTestCreateSomethingListener listener;

    private Button mButtonCreateSomething;

    private TestCreateSomethingFragment() {
    }

    public static TestCreateSomethingFragment newInstance() {
        return new TestCreateSomethingFragment();
    }

    /**
     * Se castea el contexto hacia la interfaz, y lo asignamos a nuestro listener.
     *
     * @param context
     */
    @Override
    public void onAttach(@NonNull Context context) {
        try {
            listener = (OnTestCreateSomethingListener) context;
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
        return inflater.inflate(R.layout.fragment_test_create_something, container, false);
    }

    /**
     * Con la vista creada podemos hacer los respectivos findViewById() o click listeners
     * o lo que sea que necesitemos
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mButtonCreateSomething = view.findViewById(R.id.button_test_create_something);

        mButtonCreateSomething.setOnClickListener(v -> {

            /**
             * Para comunicarnos con la activity, basta con llamar un metodo del listener y esto
             * automaticamente ejecutará el metodo que se encuentra implementado
             * en la activity que contiene este fragment
             */
            listener.onCreateSomething("Hola", 2020);
            dismiss();
        });
    }
}