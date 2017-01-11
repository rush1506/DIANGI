package model;

/**
 * Created by vutha_000 on 12/1/2016.
 */

public class EatingIndoorLocation extends EatingLocation {

    private String OpenHour;
    private String CloseHour;

    public String getOpenHour() {
        return OpenHour;
    }

    public void setOpenHour(String openHour) {
        OpenHour = openHour;
    }

    public String getCloseHour() {
        return CloseHour;
    }

    public void setCloseHour(String closeHour) {
        CloseHour = closeHour;
    }
}
