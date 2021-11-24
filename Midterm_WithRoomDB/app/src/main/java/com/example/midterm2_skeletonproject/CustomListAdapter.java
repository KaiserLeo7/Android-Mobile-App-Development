package com.example.midterm2_skeletonproject;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomListAdapter extends ArrayAdapter<Car>  {
    private final Activity context;

    Car[] carList;

    public CustomListAdapter(Activity context, Car[] carList) {
        super(context, R.layout.row_item ,carList);
        this.context = context;
        this.carList = carList;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.row_item, null,true);

        Car c = carList[position]; // get a car object based on POSITION

        TextView tvName = (TextView) rowView.findViewById(R.id.tvName);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imIcon);
        TextView tvModel = (TextView) rowView.findViewById(R.id.tvModel);
        TextView tvYear = (TextView) rowView.findViewById(R.id.tvYear);

        // set it to views
        tvName.setText(c.getOwnerName());
        imageView.setImageBitmap(c.getImageData());
        tvModel.setText(c.getModel());
        tvYear.setText(c.getYear());

        return rowView;
    }
}
