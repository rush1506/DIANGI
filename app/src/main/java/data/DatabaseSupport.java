package data;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;

import com.tata.diangi.R;

import java.util.ArrayList;

import model.Location;

/**
 * Created by vutha_000 on 12/1/2016.
 */

public class DatabaseSupport {

    static private String DATABSE_NAME = "location";
    static private String DATABASE_TABLE_LOCATION = "";
    static private String DATABASE_TABLE_EATING_LOCATION = "";
    static private String DATABASE_TABLE_DRINKING_LOCATION = "";
    static private String DATABASE_TABLE_EATING_INDOOR_LOCATION = "";
    static private String DATABASE_TABLE_DRINKING_INDOOR_LOCATION = "";
    static private String DATABASE_TABLE_FAST_FOOD = "";
    static private String DATABASE_TABLE_LOCAL = "";
    static private String DATABASE_TABLE_RESTAURANT = "";
    static private String DATABASE_TABLE_PUB = "";
    static private String DATABASE_TABLE_BOARD_GAME = "";
    static private int DATABASE_VERSION = 1;

    public static int getDatabaseVersion() {
        return DATABASE_VERSION;
    }

    public static void setDatabaseVersion(int databaseVersion) {
        DATABASE_VERSION = databaseVersion;
    }

    public static String getDatabseName() {
        return DATABSE_NAME;
    }

    public static void setDatabseName(String databseName) {
        DATABSE_NAME = databseName;
    }

    public static String getDatabaseTableLocation() {
        return DATABASE_TABLE_LOCATION;
    }

    public static void setDatabaseTableLocation(String databaseTableLocation) {
        DATABASE_TABLE_LOCATION = databaseTableLocation;
    }

    public static String getDatabaseTableEatingLocation() {
        return DATABASE_TABLE_EATING_LOCATION;
    }

    public static void setDatabaseTableEatingLocation(String databaseTableEatingLocation) {
        DATABASE_TABLE_EATING_LOCATION = databaseTableEatingLocation;
    }

    public static String getDatabaseTableDrinkingLocation() {
        return DATABASE_TABLE_DRINKING_LOCATION;
    }

    public static void setDatabaseTableDrinkingLocation(String databaseTableDrinkingLocation) {
        DATABASE_TABLE_DRINKING_LOCATION = databaseTableDrinkingLocation;
    }

    public static String getDatabaseTableEatingIndoorLocation() {
        return DATABASE_TABLE_EATING_INDOOR_LOCATION;
    }

    public static void setDatabaseTableEatingIndoorLocation(String databaseTableEatingIndoorLocation) {
        DATABASE_TABLE_EATING_INDOOR_LOCATION = databaseTableEatingIndoorLocation;
    }

    public static String getDatabaseTableDrinkingIndoorLocation() {
        return DATABASE_TABLE_DRINKING_INDOOR_LOCATION;
    }

    public static void setDatabaseTableDrinkingIndoorLocation(String databaseTableDrinkingIndoorLocation) {
        DATABASE_TABLE_DRINKING_INDOOR_LOCATION = databaseTableDrinkingIndoorLocation;
    }

    public static String getDatabaseTableFastFood() {
        return DATABASE_TABLE_FAST_FOOD;
    }

    public static void setDatabaseTableFastFood(String databaseTableFastFood) {
        DATABASE_TABLE_FAST_FOOD = databaseTableFastFood;
    }

    public static String getDatabaseTableLocal() {
        return DATABASE_TABLE_LOCAL;
    }

    public static void setDatabaseTableLocal(String databaseTableLocal) {
        DATABASE_TABLE_LOCAL = databaseTableLocal;
    }

    public static String getDatabaseTableRestaurant() {
        return DATABASE_TABLE_RESTAURANT;
    }

    public static void setDatabaseTableRestaurant(String databaseTableRestaurant) {
        DATABASE_TABLE_RESTAURANT = databaseTableRestaurant;
    }

    public static String getDatabaseTablePub() {
        return DATABASE_TABLE_PUB;
    }

    public static void setDatabaseTablePub(String databaseTablePub) {
        DATABASE_TABLE_PUB = databaseTablePub;
    }

    public static String getDatabaseTableBoardGame() {
        return DATABASE_TABLE_BOARD_GAME;
    }

    public static void setDatabaseTableBoardGame(String databaseTableBoardGame) {
        DATABASE_TABLE_BOARD_GAME = databaseTableBoardGame;
    }


    static public ArrayList<Location> searchForPlaceFromDatabase(String searchString, Context context) {

        LocationDatabaseHandler ldbHandler = new LocationDatabaseHandler(context);

        ArrayList<Location> tmpLocations = new ArrayList<>();
        tmpLocations = ldbHandler.getLocations(searchString);

        ldbHandler.close();

        return tmpLocations;
    }

}
