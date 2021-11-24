package com.example.gameofthrones_app;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Character {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private int id;

    private String characterName;
    private String actorName;
    private String characterImageThumb;

    private boolean isFav;

    Character(){}

    public Character(String city, String country, String characterImage, boolean isFav){
        this.characterName = city;
        this.actorName = country;
        this.characterImageThumb = characterImage;
        this.isFav = isFav;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }
    public String getCharacterName() {
        return characterName;
    }

    public int getId() {
        return id;
    }

    public String getActorName() {
        return actorName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getCharacterImageThumb() {
        return characterImageThumb;
    }

    public void setCharacterImageThumb(String characterImageThumb) {
        this.characterImageThumb = characterImageThumb;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }
}

