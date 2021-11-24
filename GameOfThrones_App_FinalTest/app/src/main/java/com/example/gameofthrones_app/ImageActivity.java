package com.example.gameofthrones_app;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ImageActivity extends AppCompatActivity implements NetworkingClass.APIDataListner {
ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        NetworkingClass networkingClass = new NetworkingClass(this,getApplicationContext());
        String imageURL = getIntent().getStringExtra("image");

        networkingClass.getImageFromURL(imageURL);
        image = (ImageView) findViewById(R.id.image);
    }

    @Override
    public void returnAPIData(String data) { }

    @Override
    public void returnBitmapImage(Bitmap image) {
        this.image.setImageBitmap(image);
    }
}


