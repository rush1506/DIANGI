package model;

/**
 * Created by vutha_000 on 12/1/2016.
 */

public class FastFood extends EatingIndoorLocation {
    private String DeliveryTime;
    private String SpecialDay;

    public String getDeliveryTime() {
        return DeliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        DeliveryTime = deliveryTime;
    }

    public String getSpecialDay() {
        return SpecialDay;
    }

    public void setSpecialDay(String specialDay) {
        SpecialDay = specialDay;
    }
}
