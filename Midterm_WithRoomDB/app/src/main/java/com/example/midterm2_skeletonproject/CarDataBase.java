package com.example.midterm2_skeletonproject;

import androidx.room.Database;
import androidx.room.RoomDatabase;



@Database(entities = {Car.class}, version = 1)
public abstract class CarDataBase extends RoomDatabase {
    public abstract CarDAO carDao();

}
