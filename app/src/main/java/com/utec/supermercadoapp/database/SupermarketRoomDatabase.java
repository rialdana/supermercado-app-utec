package com.utec.supermercadoapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.utec.supermercadoapp.database.dao.CategoriasDao;
import com.utec.supermercadoapp.database.dao.ProductosDao;
import com.utec.supermercadoapp.database.dao.SucurslesDao;
import com.utec.supermercadoapp.database.entities.Categorias;
import com.utec.supermercadoapp.database.entities.Productos;
import com.utec.supermercadoapp.database.entities.Sucursales;
import com.utec.supermercadoapp.database.entities.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class , Sucursales.class , Categorias.class, Productos.class}, version = 3, exportSchema = false)
public abstract class SupermarketRoomDatabase extends RoomDatabase {

    public abstract SupermarketDao supermarketDao();

    public abstract CategoriasDao categoriasDao();

    public abstract SucurslesDao sucurslesDao();

    public abstract ProductosDao productosDao();

    private static volatile SupermarketRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static SupermarketRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SupermarketRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SupermarketRoomDatabase.class, "supermarket_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

