package com.utec.supermercadoapp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.utec.supermercadoapp.database.entities.Productos;

import java.util.List;
@Dao
public interface ProductosDao {
    @Query("SELECT * FROM productos_table")
    List<Productos> Todo();

    @Query("SELECT * FROM productos_table WHERE id_sucursal = :id")
    List<Productos> Uno(int id);

    @Insert
    void Insertar(Productos producto);
}
