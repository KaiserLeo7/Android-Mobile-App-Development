package com.example.midterm2_skeletonproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.os.Bundle;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddYearAndModelDialog.GetInfoListener {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView myImage;
    ArrayList<Car> carsArrayList;
    EditText myName;

    //////////
    String pickedYear = "";
    String pickedBrand = "";
    Bitmap tempBm;
    //////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myImage = (ImageView) findViewById(R.id.personalImageID);
        myName = (EditText) findViewById(R.id.studentName);
        carsArrayList = new ArrayList<Car>(0);
        DatabaseClient.buildDbClient(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void uploade(View view) {
        //open camera app and take a photo of the car. (use an emulator fixed camera instead if you don't have physical device)
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } catch (ActivityNotFoundException e) {
            // display error state to the user
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            tempBm = imageBitmap;
            myImage.setImageBitmap(imageBitmap);
        }
    }

    public void addNewCarModelAndYear(View view) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        AddYearAndModelDialog dialogFragment = new AddYearAndModelDialog();
        dialogFragment.show(ft, "dialog");
    }

    public void saveCar(View view) {
        String name = myName.getText().toString();
        String model = this.pickedBrand;
        String year = this.pickedYear;
        Bitmap bm = this.tempBm;

        if(! name.equals("")) {
            if(!year.equals("") && !model.equals("")) {

                Car newCar = new Car(0,name, bm, year, model);
                carsArrayList.add(newCar);
                myName.setText("");

                DatabaseClient.insertNewCar(newCar);

                DatabaseClient.getAllCars();
                Intent reportIntent = new Intent(MainActivity.this, ReportActivity.class);
                reportIntent.putParcelableArrayListExtra("CarListExtra", carsArrayList);
                startActivity(reportIntent);

            } else {
                Toast.makeText(getApplicationContext(), "Please select Model & Year!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Please type your name!", Toast.LENGTH_SHORT).show();
        }
    }

    // use interface to send back data from dialog fragment
    @Override
    public void onFinishEditDialog(String brand, String year) {
        this.pickedBrand = brand;
        this.pickedYear = year;
    }
}
