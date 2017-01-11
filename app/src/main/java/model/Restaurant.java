package model;

/**
 * Created by vutha_000 on 12/1/2016.
 */

public class Restaurant extends EatingIndoorLocation {
    private float StarRating;
    private String Cuisine;
    private String MasterChef;
    private boolean MustTip;

    public float getStarRating() {
        return StarRating;
    }

    public void setStarRating(float starRating) {
        StarRating = starRating;
    }

    public String getCuisine() {
        return Cuisine;
    }

    public void setCuisine(String cuisine) {
        Cuisine = cuisine;
    }

    public String getMasterChef() {
        return MasterChef;
    }

    public void setMasterChef(String masterChef) {
        MasterChef = masterChef;
    }

    public boolean isMustTip() {
        return MustTip;
    }

    public void setMustTip(boolean mustTip) {
        MustTip = mustTip;
    }
}
