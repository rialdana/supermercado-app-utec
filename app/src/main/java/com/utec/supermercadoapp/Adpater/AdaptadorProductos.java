package com.utec.supermercadoapp.Adpater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.utec.supermercadoapp.R;
import com.utec.supermercadoapp.database.entities.Productos;

import java.util.List;

public class AdaptadorProductos extends RecyclerView.Adapter<AdaptadorProductos.ProductosViewHolder> {

    List<Productos> ProductosList;
    productosListener listener;

    public AdaptadorProductos(List<Productos> productos, productosListener listener) {
        this.ProductosList = productos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AdaptadorProductos.ProductosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_productos, parent, false);
        return new AdaptadorProductos.ProductosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorProductos.ProductosViewHolder holder, int position) {
        holder.bindData(ProductosList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return ProductosList.size();
    }

    public class ProductosViewHolder extends RecyclerView.ViewHolder {

        TextView nombre, descripcion, precio;
        private CardView cardViewSucursal;

        public ProductosViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.textView_name);
            descripcion = itemView.findViewById(R.id.textView_description);
            precio = itemView.findViewById(R.id.textView_price);
            //cardViewSucursal = itemView.findViewById(R.id.cardview_recyclerview_horizontal);

        }

        public void bindData(Productos producto, productosListener listener) {
            nombre.setText(producto.getNombre());
            descripcion.setText(producto.getDescripcion());
            precio.setText("$ " + String.valueOf(producto.getPrecio()));
        }
    }

    public void setNewList(List<Productos> productos) {
        this.ProductosList = productos;
        notifyDataSetChanged();
    }
}
