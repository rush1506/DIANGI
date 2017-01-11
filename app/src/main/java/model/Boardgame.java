package model;

/**
 * Created by vutha_000 on 12/1/2016.
 */

public class Boardgame extends DrinkingIndoorLocation {
    private String GameType;
    private float AverageRentPerHour;

    public String getGameType() {
        return GameType;
    }

    public void setGameType(String gameType) {
        GameType = gameType;
    }

    public float getAverageRentPerHour() {
        return AverageRentPerHour;
    }

    public void setAverageRentPerHour(float averageRentPerHour) {
        AverageRentPerHour = averageRentPerHour;
    }
}
