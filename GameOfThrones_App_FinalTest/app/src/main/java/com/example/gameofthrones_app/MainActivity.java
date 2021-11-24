package com.example.gameofthrones_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements  NetworkingClass.APIDataListner, CharacterAdapter.OnItemClickListener {

    ArrayList<Character> characters;
    JsonManager jsonManager;
    RecyclerView recyclerView;
    DataBaseClient dbClient;
    CharacterAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NetworkingClass networkingClass = new NetworkingClass(this, getApplicationContext());
        jsonManager = new JsonManager();
        setTitle("GOT App");
        recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //networkingClass.getGOTCharactersInfo();
        dbClient = DataBaseClient.getInstance(this);

        AssetsReader reader = new AssetsReader();
        characters = (ArrayList<Character>) reader.getCharacters(this);
        dbClient.checkAllFavoriteCharacters(characters);
        //characters = jsonManager.parseGOTData(data);
        adapter = new CharacterAdapter(this,characters);
        adapter.listner = this;
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.gotapp_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.favoriteGOT:{

                Intent favCharactersIntent = new Intent(this, FavoriteList.class);
                startActivity(favCharactersIntent);
            }
        }
        return true;
    }

    @Override
    public void returnAPIData(String data) {
        characters =  jsonManager.parseGOTData(data);
        adapter = new CharacterAdapter(this,characters);
        adapter.listner = this;
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void returnBitmapImage(Bitmap image) {}

    @Override
    public void onItemClick(Character item) {
        Intent intent = new Intent(MainActivity.this, ImageActivity.class);
        intent.putExtra("image", item.getCharacterImageThumb());
        startActivity(intent);
    }

    @Override
    public void onImageClicked(Character item) {
        //save to db, change the color
        dbClient.insertFavoriteCharacter(item);
    }
}
