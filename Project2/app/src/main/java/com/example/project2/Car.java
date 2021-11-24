package com.example.project2;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Car implements Serializable {

    @PrimaryKey
    private int id;

    private String carModel1;
    private String carModel2;
    private Double year;
    private boolean IsFavorite;

    public Car() {}

    public Car(int id, String carModel1, String carModel2, Double year, boolean favorite) {
        this.id = id;
        this.carModel1 = carModel1;
        this.carModel2 = carModel2;
        this.year = year;
        this.IsFavorite = favorite;

    }

    public int getId() {
        return id;
    }

    public String getCarModel1() {
        return carModel1;
    }

    public String getCarModel2() {
        return carModel2;
    }

    public Double getYear() {
        return year;
    }

    public boolean getIsFavorite() {
        return IsFavorite;
    }

    ///////////////////////////////////////////////////

    public void setId(int id) {
        this.id = id;
    }

    public void setCarModel1(String carModel1) {
        this.carModel1 = carModel1;
    }

    public void setCarModel2(String carModel2) {
        this.carModel2 = carModel2;
    }

    public void setYear(Double year) {
        this.year = year;
    }

    public void setIsFavorite(boolean favorite) {
        this.IsFavorite = favorite;
    }
}

