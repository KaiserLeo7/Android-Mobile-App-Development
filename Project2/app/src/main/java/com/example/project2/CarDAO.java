package com.example.project2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CarDAO {

    @Insert
    public void insertCar(Car car);

    @Query("SELECT * FROM Car")
    public List<Car> getAll();

    @Query("DELETE FROM Car WHERE id = :id")
    public void deleteCar(int id);
}
