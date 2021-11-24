package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Toast;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NetworkingManager.APIDataListener, CarAdapter.AlertDialogListener {

    NetworkingManager networkingManager;
    JsonManager jsonManager;
    CarDatabase databaseClient;
    RecyclerView recyclerView;
    private static MainActivity instance;

    private static List<Car> dbCars;
    private static List<Car> cars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;

        networkingManager = new NetworkingManager(this,getApplicationContext());
        networkingManager.getCarJson();

        jsonManager = new JsonManager();

        setTitle("Favorite Car App");
        databaseClient = DatabaseClient.buildDbClient(this);
        recyclerView = findViewById(R.id.recyclerview_cars);
        getAllCars();
    }

    public static MainActivity getInstance() {
            return instance;
    }

    public void getAllCars() {
        DatabaseClient.databaseWriteExecutor.execute(()->{
            dbCars = DatabaseClient.getDbClient().getDao().getAll();

        });
    }

    @Override
    public void returnAPIData(String data) {

        cars = jsonManager.parceCarData(data);

        for(int i=0; i < dbCars.size(); i++) {
            for (int j = 0; j < cars.size(); j++) {
                if (dbCars.get(i).getId() == cars.get(j).getId()) {
                    cars.get(j).setIsFavorite(true);
                }
            }
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(new CarAdapter(MainActivity.this,cars));
        recyclerView.invalidate();
    }


        @Override
    public void alertDialogFinishSavingCar(Car insertedCar) {
        Toast.makeText(getApplicationContext(),insertedCar.getCarModel1() +
                " " + insertedCar.getCarModel2() + " Moved",Toast.LENGTH_SHORT).show();
    }

//every time you call this func it says hey a new car was added
    //take the copy of the cars list, match which one was added
    //change that ones image based on true or false
    //push to recycler
    public void refreshAddRecycler(Car carNew) {
        getAllCars();
        for (int k=0; k < cars.size(); k++) {
            if (carNew.getId() == cars.get(k).getId()) {
                cars.get(k).setIsFavorite(true);
            }
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(new CarAdapter(MainActivity.this,cars));
        recyclerView.invalidate();
    }

    public void refreshRemoveRecycler(Car carNew) {
        getAllCars();
        for (int k=0; k < cars.size(); k++) {
            if (carNew.getId() == cars.get(k).getId()) {
                cars.get(k).setIsFavorite(false);
            }
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(new CarAdapter(MainActivity.this,cars));
        recyclerView.invalidate();
    }
}
