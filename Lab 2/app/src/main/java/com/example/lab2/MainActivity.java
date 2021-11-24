package com.example.lab2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    String BurgerList[] = {};
    String selectedBurger[] = {};

    CheckBox Cheese, Tomato, Sauce;
    Spinner burgerSpinner;
    SeekBar seekBarTip;
    TextView percentTip;
    Button Order;
    int incProgress = 0;
    boolean extras = false;
    String toppings = ",\n";
    String fullMessage = "Your ";
    String finalBurger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //link the spinner by its ID
        burgerSpinner = (Spinner) findViewById(R.id.Burgers);
        //link CheckBoxes by their ID
        Cheese = findViewById(R.id.Cheese);
        Tomato = findViewById(R.id.Tomato);
        Sauce = findViewById(R.id.Sauce);
        //link SeekBar by its ID
        seekBarTip = findViewById(R.id.SeekBarTip);
        //link PercentBar by its ID
        percentTip = findViewById(R.id.TipPercent);
        //link the button by its ID
        Order = (Button) findViewById(R.id.Order);

        //Array of all the burgers available
        BurgerList = new String[]{"Hamburger", "Cheeseburger", "BigMac", "Quarter Pounder", "McDouble"};
        //the selected burger duh
        selectedBurger = new String[1];
        //fill the selectedBurger array with an empty string
        Arrays.fill(selectedBurger, "");


        //get this array adapter, use the spinner design, and the burger list
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.spinner_row_design, R.id.BurgerTextID, BurgerList);
        burgerSpinner.setAdapter(arrayAdapter);

        Cheese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Cheese.isChecked()) {
                    toppings += "Add Cheese";
                    extras = true;
                }
            }
        });

        Tomato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Tomato.isChecked()) {
                    toppings += " Add Tomato";
                    extras = true;
                }
            }
        });

        Sauce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Sauce.isChecked()) {
                    toppings += " Add Sauce";
                    extras = true;
                }
            }
        });

        seekBarTip.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBarTip, int progress, boolean fromUser) {
                incProgress = progress * 5;
                percentTip.setText("" + incProgress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBarTip) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBarTip) {

            }
        });

        Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finalBurger = burgerSpinner.getSelectedItem().toString();
                fullMessage += finalBurger;


                if (extras)
                    fullMessage += toppings;

                fullMessage += "\nis being prepared.";

                if (incProgress > 0)
                    fullMessage += ("\nTipping " + incProgress + "%");

                        AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(MainActivity.this);
                myAlertBuilder.setTitle("Order Processed");

                //add a string here with the full message
                myAlertBuilder.setMessage(fullMessage);

                myAlertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(MainActivity.this, "Thank You!", Toast.LENGTH_SHORT).show();
                    }
                });
                myAlertBuilder.show();
            }
        });
    }
}