package com.example.gameofthrones_app;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class JsonManager {

    interface imageDownloaderCallBack {
        public void downloaderDidFinishWithImage();
    }

    public ArrayList<Character> parseGOTData(String data){

        ArrayList<Character> list = new ArrayList<>(0);
        try {

            JSONArray array =  new JSONArray(data);
            for (int i = 0 ; i< array.length(); i++){
                Character c = new Character();
                JSONObject character = array.getJSONObject(i);
                String characterName = character.getString("characterName");
                c.setCharacterName(characterName);
                if (!character.isNull("actorName")){
                    String actorName = character.getString("actorName");
                    c.setActorName(actorName);
                }
                if (!character.isNull("characterImageThumb")){
                    String image = character.getString("characterImageThumb");
                    c.setCharacterImageThumb(image);
                }

             list.add(c);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    return list;
    }
}
