package com.utec.supermercadoapp.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "productos_table")
public class Productos {

    @PrimaryKey(autoGenerate = true)
    private int id_producto;

    @NonNull
    private String nombre;
    @NonNull
    private String descripcion;
    @NonNull
    private int existencia;
    @NonNull
    private double precio;
    @NonNull
    private int id_categoria;
    @NonNull
    private int id_sucursal;


    public Productos(@NonNull String nombre, @NonNull String descripcion, int existencia, double precio, @NonNull int id_categoria, @NonNull int id_sucursal) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.existencia = existencia;
        this.precio = precio;
        this.id_categoria = id_categoria;
        this.id_sucursal = id_sucursal;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    @NonNull
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@NonNull String descripcion) {
        this.descripcion = descripcion;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @NonNull
    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(@NonNull int id_categoria) {
        this.id_categoria = id_categoria;
    }

    @NonNull
    public int getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(@NonNull int id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }
}
