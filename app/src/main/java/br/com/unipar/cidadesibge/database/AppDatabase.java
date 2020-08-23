package br.com.unipar.cidadesibge.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.com.unipar.cidadesibge.dao.CidadeDao;
import br.com.unipar.cidadesibge.entities.Cidade;


@Database(entities = {Cidade.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CidadeDao cidadeDao();

    public static AppDatabase getCommection(Context appContext) {
        return Room.databaseBuilder(appContext, AppDatabase.class, "app_cidades").build();
    }
}
