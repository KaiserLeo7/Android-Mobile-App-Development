package com.example.project2;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Car.class}, version = 1)
public abstract class CarDatabase extends RoomDatabase {
    public abstract CarDAO getDao();

}