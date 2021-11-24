package com.example.donationappv2;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Donations.class}, version = 1)
public abstract class DonationsDatabase extends RoomDatabase {
    public abstract DonationsDAO DonationDao();

}
