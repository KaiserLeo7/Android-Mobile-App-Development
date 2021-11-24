package com.example.gameofthrones_app;

import android.content.Context;

import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class DataBaseClient {

    private static AppDataBase sDatabase;
    public static DataBaseClient sDBInstance;

    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(4);

    public static DataBaseClient getInstance(Context context){

        if (sDatabase == null) {
            sDBInstance = new DataBaseClient();
            sDBInstance.sDatabase = Room.databaseBuilder(context, AppDataBase.class, "CharactersDB").build();
        }
        return sDBInstance;
    }

    public static AppDataBase getsDatabase(){
        return sDatabase;
    }

    public static DataBaseClient getDataBaseClient(){
        return sDBInstance;
    }

    public void insertFavoriteCharacter(final Character character){
        databaseWriteExecutor.execute(()->{
            character.setFav(true);
            getsDatabase().getDao().insertCharacter(character);
        });
    }

    public ArrayList<Character> checkAllFavoriteCharacters(ArrayList<Character> list){
        for (Character c : list) {
            checkIfFavorite(c);
        }
        return list;
    }

    public void checkIfFavorite(final Character character){
        databaseWriteExecutor.execute(()->{
            Character inDB = getsDatabase().getDao().checkIfFav(character.getCharacterName());
            if (inDB != null)
                character.setFav(false);
            else
                character.setFav(false);
        });
    }

}
