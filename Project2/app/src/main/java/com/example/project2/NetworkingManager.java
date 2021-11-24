package com.example.project2;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkingManager {

    APIDataListener activity;
    Context mainActivityContext;
    final String url = "https://raw.githubusercontent.com/RaniaArbash/Assignment2_SkeletonProject/master/cars.json";

    public  interface APIDataListener {
        public void returnAPIData(String data);
    }

    NetworkingManager(APIDataListener listener, Context context){
        this.activity = listener;
        mainActivityContext = context;
    }

    void getCarJson() {
        connectAnAPI(url);
    }

    void connectAnAPI(final String url) {
        try {

            Thread thread = new Thread() {
                public void run () {
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
                                httpURLConnection.setRequestProperty("Content-Type" , "application/json");
                                int status = httpURLConnection.getResponseCode();
                                Log.d("GET RX", " status => " + status);//200 is ok

                                try {
                                    InputStream in = httpURLConnection.getInputStream();
                                    InputStreamReader inputStreamReader = new InputStreamReader(in);
                                    int inputStreamData = inputStreamReader.read();
                                    while (inputStreamData != -1) {
                                        char current = (char) inputStreamData;
                                        inputStreamData = inputStreamReader.read();
                                        data += current;
                                    }
                                    String finalData = data;
                                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                                        @Override
                                        public void run() {
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
                    }, 1000);

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
