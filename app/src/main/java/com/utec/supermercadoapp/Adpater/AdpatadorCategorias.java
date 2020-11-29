package com.utec.supermercadoapp.Adpater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.utec.supermercadoapp.R;
import com.utec.supermercadoapp.database.entities.Categorias;

import java.util.List;

public class AdpatadorCategorias extends RecyclerView.Adapter<AdpatadorCategorias.CategoriasViewHolder> {

    private List<Categorias> categoriasList;
    private categoriasListener listener;

    public AdpatadorCategorias(List<Categorias> categorias, categoriasListener listener) {
        this.categoriasList = categorias;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoriasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_horizontal, parent, false);
        return new CategoriasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriasViewHolder holder, int position) {
        holder.bindData(categoriasList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return categoriasList.size();
    }


    public static class CategoriasViewHolder extends RecyclerView.ViewHolder {

        TextView textViewContinentName;


        public CategoriasViewHolder (@NonNull View itemView) {
            super(itemView);
            textViewContinentName = itemView.findViewById(R.id.textView_Categoria);

        }

        public void bindData(final Categorias categoria, final categoriasListener listener) {
            textViewContinentName.setText(categoria.getCategoria());
        }
    }
}
