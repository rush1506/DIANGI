package model;

/**
 * Created by vutha_000 on 12/1/2016.
 */

public class Pub extends Local {
    private String Beverage;
    private String SpecialActivity;

    public String getBeverage() {
        return Beverage;
    }

    public void setBeverage(String beverage) {
        Beverage = beverage;
    }

    public String getSpecialActivity() {
        return SpecialActivity;
    }

    public void setSpecialActivity(String specialActivity) {
        SpecialActivity = specialActivity;
    }
}
