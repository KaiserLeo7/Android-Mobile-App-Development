package com.example.project2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.util.ArrayList;

    public class JsonManager {

        public ArrayList<Car> parceCarData(String json){
            ArrayList<Car> carFromAPI = new ArrayList<>();

            JSONObject jsonObject = null;
            try {
                JSONArray array =  new JSONArray(json);
                for (int i = 0 ; i< array.length(); i++){
                    String fullCar = array.getString(i);

                    //get the characters from string starting after ":" and ending after ","
                    String idString = fullCar.substring(fullCar.indexOf(":") + 1,fullCar.indexOf(','));
                    //Turn idString to int
                    int id = Integer.parseInt(idString);

                    //new string holds everything after id's ","
                    String noId = fullCar.substring(fullCar.indexOf(",") + 2);
                    //get carModel1 starting after ":" and ending after ","
                    String carModel1 = noId.substring(noId.indexOf(":") + 2, noId.indexOf(",") - 1);

                    //new string holds everything after carModel1 ","
                    String noCarModel1 = noId.substring(noId.indexOf(",") + 2);
                    //get carModel2 starting after ":" and ending after ","
                    String carModel2 = noCarModel1.substring(noCarModel1.indexOf(":") + 2, noCarModel1.indexOf(',') - 1);

                    //new string holds everything after carModel2 ","
                    String noCarModel2 = noCarModel1.substring(noCarModel1.indexOf(",") + 2);
                    //get yearString starting after ":" and ending after ","
                    String yearString = noCarModel2.substring(noCarModel2.indexOf(":") + 1, noCarModel2.indexOf('}'));
                    //Turn yearString to double
                    Double year = Double.parseDouble(yearString);

                    //Make new Car
                    Car car = new Car(id, carModel1, carModel2, year, false);

                    //add car to ArrayList<Car>
                    carFromAPI.add(car);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return carFromAPI;
        }

}
