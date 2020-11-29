package com.utec.supermercadoapp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import com.utec.supermercadoapp.database.entities.Sucursales;

import java.util.List;

@Dao
public interface SucurslesDao {

    @Query("SELECT * FROM sucursales_table")
    public List<Sucursales> Todo();

    @Query("SELECT * FROM sucursales_table WHERE id_sucursal = :id")
    public List<Sucursales> Uno(int id);

    @Insert
    public void Insertar(Sucursales sucursal);


}
