package com.example.donationappv2;

import android.content.Context;

import androidx.room.Room;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatabaseClient {

    private static DonationsDatabase dbClient;

    public static DonationsDatabase buildDbClient(Context context) {
        if (dbClient == null) {
            dbClient = Room.databaseBuilder(context, DonationsDatabase.class, "donations_db").build();
        }
        return dbClient;
    }

    public static DonationsDatabase getDbClient() {
        return dbClient;
    }

    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(4);

    public static void insertNewDonations(Donations donationsToInsert) {
        databaseWriteExecutor.execute(()->{
            getDbClient().DonationDao().insertDonation(donationsToInsert);
        });

    }
}

