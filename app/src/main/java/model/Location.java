package model;

import android.graphics.Bitmap;

/**
 * Created by vutha_000 on 12/1/2016.
 */

public class Location {
    private String Address;
    private String name;
    private int ID;
    private float AveragePrice;
    private float AverageUserRating;
    private String Wifi;
    private float AverageServedTime;
    private String LocationPicture;

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public float getAveragePrice() {
        return AveragePrice;
    }

    public void setAveragePrice(float averagePrice) {
        AveragePrice = averagePrice;
    }

    public float getAverageUserRating() {
        return AverageUserRating;
    }

    public void setAverageUserRating(float averageUserRating) {
        AverageUserRating = averageUserRating;
    }

    public String isWifiable() {
        return Wifi;
    }

    public void setWifi(String wifi) {
        Wifi = wifi;
    }

    public float getAverageServedTime() {
        return AverageServedTime;
    }

    public void setAverageServedTime(float averageServedTime) {
        AverageServedTime = averageServedTime;
    }

    public String getLocationPicture() {
        return LocationPicture;
    }

    public void setLocationPicture(String locationPicture) {
        LocationPicture = locationPicture;
    }
}
