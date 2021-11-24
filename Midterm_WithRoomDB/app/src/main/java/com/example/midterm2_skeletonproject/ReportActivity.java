package com.example.midterm2_skeletonproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ReportActivity extends AppCompatActivity {

    ListView list;
    CustomListAdapter adapter;
    Activity appContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        appContext = this;
//        carList = getIntent().getParcelableArrayListExtra("CarListExtra"); // receive array list of Cars from Main activity


        DatabaseClient.databaseWriteExecutor.execute(()->{ //Runneble Command
            Car[] carsFromDB =  DatabaseClient.getdbClient().carDao().loadAllCar();
            list = (ListView) findViewById(R.id.myCarList);

            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter = new CustomListAdapter(appContext, carsFromDB);
                    list.setAdapter(adapter);
                }
            });

        });


//        list = (ListView) findViewById(R.id.myCarList);
//        adapter = new CustomListAdapter(this, carList);
//        list.setAdapter(adapter);

    }




}
