package com.example.gameofthrones_app;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static com.example.gameofthrones_app.DataBaseClient.sDBInstance;

public class FavoriteList extends AppCompatActivity {

    private RecyclerView favRecyclerView;
    static FavoriteCharAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_characters);

        favRecyclerView = findViewById(R.id.favoriteList);
        favRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        getAllFavChars();
    }

    public void getAllFavChars() {
        sDBInstance.databaseWriteExecutor.execute(()->{
            List<Character> dbChars = DataBaseClient.getsDatabase().getDao().getAll();
            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter = new FavoriteCharAdapter(getApplicationContext(), dbChars);
                    favRecyclerView.setAdapter(adapter);
                }
            });
        });
    }

}



