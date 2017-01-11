package model;

/**
 * Created by vutha_000 on 12/1/2016.
 */

public class EatingLocation extends Location {
    private String FoodSpecialty;

    public String getFoodSpecialty() {
        return FoodSpecialty;
    }

    public void setFoodSpecialty(String foodSpecialty) {
        FoodSpecialty = foodSpecialty;
    }
}
