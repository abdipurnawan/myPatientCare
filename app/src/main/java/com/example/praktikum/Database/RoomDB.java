package com.example.praktikum.Database;

import android.content.Context;

import com.example.praktikum.Dao.PendaftaranDao;
import com.example.praktikum.Dao.PoliDao;
import com.example.praktikum.Dao.UserDao;
import com.example.praktikum.Model.Pendaftaran;
import com.example.praktikum.Model.Poli;
import com.example.praktikum.Model.User;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Pendaftaran.class, Poli.class},version = 1,exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    private static RoomDB database;
    private static String DATABASE_NAME = "database";

    public synchronized static  RoomDB getInstance(Context context){
        if(database == null){
            database = Room.databaseBuilder(context.getApplicationContext(), RoomDB.class, DATABASE_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }

        return database;
    }

    public abstract UserDao userDao();
    public abstract PendaftaranDao pendaftaranDao();
    public abstract PoliDao poliDao();
}