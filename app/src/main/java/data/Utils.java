package data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by vutha_000 on 1/5/2017.
 */

public class Utils {
    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/forecast/city?id=";
    public static final String ICON_URL = "http://openweathermap.org/img/w/";

    public static JSONObject getObject (String tagName, JSONObject jsonObject) {
       try {
        JSONObject jObj = jsonObject.getJSONObject(tagName);
        return jObj;
       } catch ( JSONException e) {
           e.getMessage();
           return null;
       }
    }

    public static JSONArray getJSONArray(String tagName, JSONObject jsonObject) throws JSONException {
        return jsonObject.getJSONArray(tagName);
    }

    public static String getString (String tagName, JSONObject jsonObject) throws JSONException {
        return jsonObject.getString(tagName);
    }

    public static float getFloat (String tagName, JSONObject jsonObject) throws JSONException {
        return (float) jsonObject.getDouble(tagName);
    }

    public static double getDouble (String tagName, JSONObject jsonObject) throws JSONException {
        return jsonObject.getDouble(tagName);
    }

    public static int getInt (String tagName, JSONObject jsonObject) throws JSONException {
        return jsonObject.getInt(tagName);
    }
}
