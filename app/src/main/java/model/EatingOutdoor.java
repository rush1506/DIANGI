package model;

/**
 * Created by vutha_000 on 12/1/2016.
 */

public class EatingOutdoor extends EatingLocation {

    private Weather CurrentWeather;

    public Weather getCurrentWeather() {
        return CurrentWeather;
    }

    public void setCurrentWeather(Weather currentWeather) {
        CurrentWeather = currentWeather;
    }
}
