package com.utec.supermercadoapp.Adpater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.utec.supermercadoapp.R;
import com.utec.supermercadoapp.database.entities.Sucursales;

import java.util.List;

public class AdpatadorSucursales extends RecyclerView.Adapter<AdpatadorSucursales.SucursalesViewHolder> {

    List<Sucursales> SucursaleList;
    sucursalesListener listener;

    public AdpatadorSucursales(List<Sucursales> sucursales, sucursalesListener listener) {
        this.SucursaleList = sucursales;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AdpatadorSucursales.SucursalesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_vertical, parent, false);
        return new AdpatadorSucursales.SucursalesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdpatadorSucursales.SucursalesViewHolder holder, int position) {
        holder.bindData(SucursaleList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return SucursaleList.size();
    }

    public class SucursalesViewHolder extends RecyclerView.ViewHolder {

        TextView nombre, direccion, telefono;
        private CardView cardViewSucursal;

        public SucursalesViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.textView_name);
            direccion = itemView.findViewById(R.id.textView_description);
            telefono = itemView.findViewById(R.id.textView_phone);
            cardViewSucursal = itemView.findViewById(R.id.cardview_recyclerview_horizontal);

        }

        public void bindData(Sucursales sucursales, sucursalesListener listener) {
            nombre.setText(sucursales.getNombre());
            direccion.setText(sucursales.getDirecion());
            telefono.setText(sucursales.getTelefono());

            cardViewSucursal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.selectSucursales(sucursales);
                }
            });
        }
    }

    public void setNewList(List<Sucursales> SucursaleList) {
        this.SucursaleList = SucursaleList;
        notifyDataSetChanged();
    }
}
