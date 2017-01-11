package data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.Place;
import model.Weather;

/**
 * Created by vutha_000 on 1/5/2017.
 */

public class JSONWeatherParser {
    public static Weather getWeather(String data) {

        Weather weather = new Weather();

        //create JSON object from data

        try {
            JSONObject jsonObject = new JSONObject(data);

            Place place = new Place();

            //get city object
            JSONObject cityObj = Utils.getObject("city", jsonObject);

            place.setCountry(Utils.getString("country", cityObj));
            place.setCity(Utils.getString("name", cityObj));
            JSONObject coorObj = Utils.getObject("coord", cityObj);
            place.setLat(Utils.getFloat("lat", coorObj));
            place.setLon(Utils.getFloat("lon", coorObj));



            weather.place = place;
            JSONArray listArr = Utils.getJSONArray("list", jsonObject);
            //get weather info
            JSONArray jsonArray = Utils.getJSONArray("weather", listArr.getJSONObject(0));
            JSONObject jsonWeather = jsonArray.getJSONObject(0);
            weather.currentCondition.setWeatherID(Utils.getInt("id", jsonWeather));
            weather.currentCondition.setDescription(Utils.getString("description", jsonWeather));
            weather.currentCondition.setCondition(Utils.getString("main", jsonWeather));
            weather.currentCondition.setIcon(Utils.getString("icon", jsonWeather));
            //get list object

            JSONObject mainObj = Utils.getObject("main", listArr.getJSONObject(0));

            weather.currentCondition.setHumidity(Utils.getInt("humidity", mainObj));
            weather.currentCondition.setPressure(Utils.getInt("pressure", mainObj));
            weather.currentCondition.setMinTemp(Utils.getFloat("temp_min", mainObj));
            weather.currentCondition.setMaxTemp(Utils.getFloat("temp_max", mainObj));
            weather.currentCondition.setTemperature(Utils.getFloat("temp", mainObj) - 273);


            //get wind
            JSONObject windObj = Utils.getObject("wind", listArr.getJSONObject(0));
            weather.wind.setSpeed(Utils.getFloat("speed", windObj));

            //Some how there aren't any data about degree of wind in JSONobject :/
            weather.wind.setDeg(Utils.getFloat("deg", windObj));

            //get cloud
            JSONObject cloudObj = Utils.getObject("clouds", listArr.getJSONObject(0));
            weather.clouds.setPrecipitation(Utils.getInt("all", cloudObj));

            return weather;






        } catch (JSONException e){
            e.printStackTrace();
        }

        return null;

    }
}
