package com.example.gameofthrones_app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkingClass  {

    APIDataListner activity;
    Context mainActivityContext;

    public  interface APIDataListner{
        public void returnAPIData(String data);
        void returnBitmapImage(Bitmap image);
    }

    NetworkingClass(APIDataListner listner,Context context){
        this.activity = listner;
        mainActivityContext = context;
    }

    void getImageFromURL( final String url) {
        try {
            Thread thread = new Thread() {

                public void run() {
                    Looper.prepare();
                    final Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(url).getContent());
                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        //do stuff in main activity
                                         activity.returnBitmapImage(bitmap);
                                    }
                                });
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    Looper.loop();
                }

            };
            thread.start();
        }catch (Exception e){
        }
    }

    void connectAnAPI(final String url){
        try {
            Thread thread = new Thread() {

                public void run() {
                    Looper.prepare();
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            String data = "";
                            HttpURLConnection httpURLConnection = null;
                            try {
                                httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
                                httpURLConnection.setRequestMethod("GET");
                                httpURLConnection.setRequestProperty("Content-Type", "application/json");
                                int status = httpURLConnection.getResponseCode();
                                Log.d("GET RX", " status=> " + status);
                                try {
                                    InputStream in = httpURLConnection.getInputStream();
                                    InputStreamReader inputStreamReader = new InputStreamReader(in);
                                    int inputStreamData = inputStreamReader.read();
                                    while (inputStreamData != -1) {
                                        char current = (char) inputStreamData;
                                        inputStreamData = inputStreamReader.read();
                                        data += current;
                                    }
                                    final String finalData = data;
                                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                                        @Override
                                        public void run() {
                                            //do stuff in main activity
                                           activity.returnAPIData(finalData);
                                        }
                                    });
                                }
                                catch (Exception exx) {
                                    Log.d("error", exx.toString());
                                }
                            }
                            catch (Exception e) {
                                Log.e("TX", " error => " + e.getMessage());
                                e.printStackTrace();
                            } finally {
                                if (httpURLConnection != null) {
                                    httpURLConnection.disconnect();
                                }
                            }
                            handler.removeCallbacks(this);
                            Looper.myLooper().quit();
                        }
                    }, 1);
                    Looper.loop();
                }
            };
            thread.start();
        } catch (Exception ex) {
            Log.e("ERROR =>", "" + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
