package data;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by vutha_000 on 1/5/2017.
 */
public class CityPreference {

    SharedPreferences prefs;

    public CityPreference (Activity activity) {

        prefs = activity.getPreferences(Activity.MODE_PRIVATE);
    }

    public String getCity() {

        return prefs.getString("city", "Thanh pho Ho Chi Minh, VN");
    }

    public void setCity(String city) {

        prefs.edit().putString("city", city).commit();
    }

}
