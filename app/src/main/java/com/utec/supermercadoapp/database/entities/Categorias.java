package com.utec.supermercadoapp.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "categorias_table")
public class Categorias {

    @PrimaryKey(autoGenerate = true)
    private int id_categoria;

    @NonNull
    private String Categoria;

    public Categorias() {

    }

    public Categorias(@NonNull String categoria) {
        this.Categoria = categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public void setCategoria(@NonNull String categoria) {
        Categoria = categoria;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    @NonNull
    public String getCategoria() {
        return Categoria;
    }
}
