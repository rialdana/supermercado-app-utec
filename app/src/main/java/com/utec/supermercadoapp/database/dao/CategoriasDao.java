package com.utec.supermercadoapp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.utec.supermercadoapp.database.entities.Categorias;

import java.util.List;

@Dao
public interface CategoriasDao {

    @Query("SELECT * FROM categorias_table")
    List<Categorias> Todo();

    @Query("SELECT * FROM categorias_table WHERE id_categoria = :id")
    Categorias Uno(int id);

    @Insert
    void Insertar(Categorias categoria);

}
