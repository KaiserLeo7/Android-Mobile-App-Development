package com.example.donationappv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;

import java.util.List;


public class AllDonations extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private RecyclerView recyclerView;
    static DonationAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_donations);

        recyclerView = findViewById(R.id.recyclerview_donations);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SearchView searchView = (SearchView) findViewById(R.id.action_search);
        searchView.setOnQueryTextListener(this);
        GetAll();
    }

    public void GetAll () {
        DatabaseClient.databaseWriteExecutor.execute(()->{
            List<Donations> donationsFromDB = DatabaseClient.getDbClient().DonationDao().getAll();
            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    adapter = new DonationAdapter(getApplicationContext(), donationsFromDB);
                    recyclerView.setAdapter(adapter);
                }
            });
        });
    }

    public void GetOver(String str) {
        DatabaseClient.databaseWriteExecutor.execute(()->{
            this.runOnUiThread(new Runnable() {

                double dnum = Double.parseDouble(str);

                List<Donations> donationsFromDB = DatabaseClient.getDbClient().DonationDao().getDonationsOver(dnum);

                @Override
                public void run() {

                    adapter = new DonationAdapter(getApplicationContext(), donationsFromDB);
                    recyclerView.setAdapter(adapter);
                }
            });
        });

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String str) {
        if(str.equals(""))
            str = "0";
        GetOver(str);
        return false;
    }
}
