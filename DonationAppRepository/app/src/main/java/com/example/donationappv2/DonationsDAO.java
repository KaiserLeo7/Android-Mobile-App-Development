package com.example.donationappv2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DonationsDAO {

    @Insert
    public void insertDonation(Donations donation);

    @Query("SELECT * FROM Donations")
    public List<Donations> getAll();

    @Query("SELECT * FROM Donations WHERE amount > :number")
    public  List<Donations> getDonationsOver(double number);

}
