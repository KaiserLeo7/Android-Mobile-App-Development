package com.example.gameofthrones_app;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CharacterDao {

    @Query("SELECT * FROM Character")
    public List<Character> getAll();

    @Query("SELECT * FROM Character where characterName like :cName")
    public Character checkIfFav(String cName);

    @Insert
    public void insertCharacter(Character character);

}
