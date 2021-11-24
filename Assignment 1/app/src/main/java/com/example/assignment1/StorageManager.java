package com.example.assignment1;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class StorageManager {

    public void saveInternal(Activity activity, String scoreString) throws IOException {

        FileOutputStream fileOutputStream = null;

        try {
        //    File file = activity.getFilesDir();
            fileOutputStream = activity.openFileOutput("userData.txt", Context.MODE_APPEND);
            fileOutputStream.write(scoreString.getBytes());

            return;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String loadFromInternal(Activity activity) throws IOException {
        String loadedData = "";

        try {
            FileInputStream fileInputStream = activity.openFileInput("userData.txt");
            int read = -1;
            StringBuffer buffer = new StringBuffer();

            while ((read = fileInputStream.read()) != -1) {
                buffer.append((char) read);
            }

            loadedData = buffer.toString();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return loadedData;
    }


    public final void clearInternalData(Activity activity) throws IOException {

        String blankString = "";

            FileOutputStream fileOutputStream = null;
            try {

                File file = activity.getFilesDir();

                // create a stream in write mode
                fileOutputStream = activity.openFileOutput("userData.txt", Context.MODE_PRIVATE);
                fileOutputStream.write(blankString.getBytes());

                return;
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

    }
}

