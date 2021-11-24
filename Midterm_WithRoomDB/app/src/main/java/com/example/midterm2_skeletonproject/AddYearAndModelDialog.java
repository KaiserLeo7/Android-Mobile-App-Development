package com.example.midterm2_skeletonproject;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

public class AddYearAndModelDialog extends DialogFragment {
    NumberPicker year_picker;
    Spinner modelSpinner;
    Button btnSave;

    Context app_context;

    String [] brands = {"Toyota", "Nissan", "Mercedes", "Audi", "Ferrari", "Lamborghini", "Honda"};

    int NUMBER_OF_VALUES = 30;
    int PICKER_RANGE = 1990;
    String[] displayedValues  = new String[NUMBER_OF_VALUES];



    String pickedBrand = "";
    String pickedYearModel = "";

    public AddYearAndModelDialog() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_year_level,container,false);
        year_picker = (NumberPicker) view.findViewById(R.id.npYearModel);
        modelSpinner = (Spinner) view.findViewById(R.id.spnBrand);
        initializeValues();


        btnSave = (Button) view.findViewById(R.id.btnSaveInfo);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickedBrand = modelSpinner.getSelectedItem().toString();
                if(pickedYearModel != "") {
                    GetInfoListener listener = (GetInfoListener) getActivity();
                    listener.onFinishEditDialog(pickedBrand, pickedYearModel);

                    dismiss();
                } else {
                    Toast.makeText(getContext(), "Please select year model", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, brands);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        modelSpinner.setAdapter(adapter);

        year_picker.setMinValue(0);
        year_picker.setMaxValue(displayedValues.length-1);
        year_picker.setDisplayedValues(displayedValues);
        year_picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                pickedYearModel = displayedValues[year_picker.getValue()];
            }
        });

        return view;
    }

    public interface GetInfoListener {
        void onFinishEditDialog(String brand, String year);
    }


    void initializeValues() {
        for(int i = 0; i < NUMBER_OF_VALUES; i++)
            displayedValues[i] = String.valueOf(PICKER_RANGE + i);
    }
}
