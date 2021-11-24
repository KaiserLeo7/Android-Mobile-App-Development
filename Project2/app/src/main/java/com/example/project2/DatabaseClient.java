package com.example.project2;

import android.content.Context;
import androidx.room.Room;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatabaseClient {

    private static CarDatabase dbClient;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(4);

    public static CarDatabase buildDbClient(Context context) {
        if (dbClient == null) {
            dbClient = Room.databaseBuilder(context, CarDatabase.class, "car_db").build();
        }
        return dbClient;
    }

    public static CarDatabase getDbClient() {
        return dbClient;
    }

    public static void insertNewCar(Car carToInsert) {
        databaseWriteExecutor.execute(()->{

            getDbClient().getDao().insertCar(carToInsert);
        });
    }

    public static void deleteNewCar(int id) {
        databaseWriteExecutor.execute(()->{
            getDbClient().getDao().deleteCar(id);
        });
    }


}

