package com.utec.supermercadoapp.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sucursales_table")
public class Sucursales {

    @PrimaryKey(autoGenerate = true)
    private int id_sucursal;

    @NonNull
    private String Nombre;
    @NonNull
    private String Direcion;
    @NonNull
    private  String Telefono;

    public Sucursales() {

    }


    public Sucursales(@NonNull String nombre, @NonNull String direcion, @NonNull String telefono) {
        this.Nombre = nombre;
        this.Direcion = direcion;
        this.Telefono = telefono;
    }

    public int getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(int id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    public void setNombre(@NonNull String nombre) {
        Nombre = nombre;
    }

    public void setDirecion(@NonNull String direcion) {
        Direcion = direcion;
    }

    public void setTelefono(@NonNull String telefono) {
        Telefono = telefono;
    }

    @NonNull
    public String getNombre() {
        return Nombre;
    }

    @NonNull
    public String getDirecion() {
        return Direcion;
    }

    @NonNull
    public String getTelefono() {
        return Telefono;
    }
}

