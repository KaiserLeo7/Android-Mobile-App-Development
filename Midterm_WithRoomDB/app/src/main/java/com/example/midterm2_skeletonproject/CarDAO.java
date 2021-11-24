package com.example.midterm2_skeletonproject;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface CarDAO {

    @Query("SELECT * FROM Car")
    public Car[] loadAllCar();

    @Query("SELECT * FROM Car WHERE year > :minYear")
    public Car[] loadAllUsersOlderThan(int minYear);

    @Insert
    public void insertNewCar(Car car);

    @Update
    public void updateCar(Car car);

    @Delete
    public void deleteCar(Car car);
}
