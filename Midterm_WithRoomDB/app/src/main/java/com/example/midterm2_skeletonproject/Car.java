package com.example.midterm2_skeletonproject;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity
public class Car implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "owner_name")
    String OwnerName;

    String year;
    String model;

    @Ignore
    Bitmap imageData;

    public Car(int id, String ownerName, Bitmap imageData, String year, String model) {
        this.id = id;
        this.OwnerName = ownerName;
        this.imageData = imageData;
        this.year = year;
        this.model = model;
    }

    public Car(){}
    public Car(Parcel in){
        id = in.readInt();
        OwnerName = in.readString();
        year = in.readString();
        model = in.readString();
        imageData = (Bitmap) in.readValue(Bitmap.class.getClassLoader());
    }

    public String getOwnerName() {
        return this.OwnerName;
    }

    public Bitmap getImageData() {
        return imageData;
    }

    public String getYear() {
        return this.year;
    }

    public String getModel() {
        return this.model;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(OwnerName);
        dest.writeString(year);
        dest.writeString(model);
        dest.writeValue(imageData);
    }

    public static final Parcelable.Creator<Car> CREATOR = new Parcelable.Creator<Car>(){
        public Car createFromParcel(Parcel in){
            return new Car(in);
        }

        public Car[] newArray (int size){
            return new Car[size];
        }
    };
}
