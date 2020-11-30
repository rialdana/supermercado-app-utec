package com.utec.supermercadoapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputLayout;
import com.utec.supermercadoapp.R;
import com.utec.supermercadoapp.database.SupermarketRoomDatabase;
import com.utec.supermercadoapp.database.dao.CategoriasDao;
import com.utec.supermercadoapp.database.entities.Productos;
import com.utec.supermercadoapp.listeners.ProductosFragmentListener;

import java.util.ArrayList;
import java.util.Objects;

public class Fragment_Producto extends BottomSheetDialogFragment {

    private TextInputLayout Nombre, Descripcion,Precio;
    public Spinner categorias;
    public int idSucursal;
    private CategoriasDao categoriasDao;


    /**
     * Hay que definir un listener, este listener es la interfaz mediante la cual nos vamos
     * a comunicar con la activity que contiene este fragment
     */
    private ProductosFragmentListener listener;
    private Fragment_Producto() {
    }

    public static Fragment_Producto newInstance() {
        return new Fragment_Producto();
    }

    /**
     * Se castea el contexto hacia la interfaz, y lo asignamos a nuestro listener.
     *
     * @param context
     */
    @Override
    public void onAttach(@NonNull Context context) {
        try {
            listener = (ProductosFragmentListener) context;
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
        View view;
        return inflater.inflate(R.layout.fragment__producto, container, false);


    }

    /**
     * Con la vista creada podemos hacer los respectivos findViewById() o click listeners
     * o lo que sea que necesitemos
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        Nombre = view.findViewById(R.id.text_input_layout_nombre_producto);
        Descripcion = view.findViewById(R.id.text_input_layout_descripcion_producto);
        Precio = view.findViewById(R.id.text_input_layout_precio_producto);
        categorias=view.findViewById(R.id.spinner_categorias);
        SupermarketRoomDatabase db = SupermarketRoomDatabase.getDatabase(getContext());
        categoriasDao=db.categoriasDao();
        SupermarketRoomDatabase.databaseWriteExecutor.execute(() -> {
            ArrayList<String> lista = new ArrayList<>();
            ArrayAdapter adapter;
            for (int i=0;i<categoriasDao.Todo().size();i++)
            {
                lista.add(categoriasDao.Todo().get(i).getCategoria());
            }
            adapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_list_item_1, lista);
            categorias.setAdapter(adapter);
        });

        view.findViewById(R.id.button_guardar_producto).setOnClickListener(v -> {
            String nombre = Objects.requireNonNull(Nombre.getEditText()).getText().toString();
            String descripcion =  Objects.requireNonNull(Descripcion.getEditText()).getText().toString();
            String precio =  Objects.requireNonNull(Precio.getEditText()).getText().toString();
            Log.i("TAG", "john: "+ categorias.getSelectedItemId());
            int idCat=Objects.requireNonNull(Integer.parseInt(String.valueOf(categorias.getSelectedItemId())));
            if(nombre.isEmpty())
            {
                Toast.makeText(this.getActivity(), "Debe ingresar un nombre",Toast.LENGTH_LONG).show();
                Nombre.requestFocus();
                return;
            }
            if(descripcion.isEmpty())
            {
                Toast.makeText(this.getActivity(), "Debe ingresar una descripcion",Toast.LENGTH_LONG).show();
                Descripcion.requestFocus();
                return;
            }
            if(precio.isEmpty())
            {
                Toast.makeText(this.getActivity(), "Debe ingresar un precio",Toast.LENGTH_LONG).show();
                Precio.requestFocus();
                return;
            }
            Productos producto = new Productos(nombre,descripcion,3,Double.valueOf(precio),(idCat+1),idSucursal);
            listener.InsertarProducto(producto);
            dismiss();
        });
    }
}