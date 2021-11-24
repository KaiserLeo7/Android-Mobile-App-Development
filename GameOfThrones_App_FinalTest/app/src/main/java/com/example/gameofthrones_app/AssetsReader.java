package com.example.gameofthrones_app;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AssetsReader {

    private String readFromAsset(final Activity act, final String fileName)
    {
        String text = "";
        try {
            InputStream is = act.getAssets().open(fileName);
            int size = is.available();
            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            text = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    public List<Character> getCharacters(final Activity activity) {

        Set<Character> characterList = new HashSet<>();

        String json = readFromAsset(activity, "GOT.json");

        Type listType = new TypeToken<HashSet<Character>>() {}.getType();
        // convert json into a list of Users
        try {
            characterList = new Gson().fromJson(json, listType);
        }
        catch (Exception e) {
            // we never know :)
            Log.e("error parsing", e.toString());
        }
        List<Character> listFromSet = new ArrayList<Character>(characterList);
        return listFromSet;
    }
}
