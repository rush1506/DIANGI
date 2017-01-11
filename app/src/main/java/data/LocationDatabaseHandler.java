package data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.util.ArrayList;

import constant.LocationConstant;
import model.Location;

/**
 * Created by vutha_000 on 12/1/2016.
 */

public class LocationDatabaseHandler extends SQLiteOpenHelper {


    private static final String TAG_ADD = "Add";

    private static final String TAG_DELETE = "Delete";

    private final ArrayList<Location> Locationsdb = new ArrayList<>();


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + LocationConstant.TABLE_NAME);
        //Make new db
        onCreate(sqLiteDatabase);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS " + LocationConstant.TABLE_NAME + "(" +
                LocationConstant.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                LocationConstant.LOCATION_NAME + " TEXT NOT NULL, " +
                LocationConstant.ADDRESS_NAME + " TEXT NOT NULL, " +
                LocationConstant.WIFI_NAME + " TEXT, " +
                LocationConstant.AVERAGE_PRICE_NAME + " REAL, " +
                LocationConstant.AVERAGE_USER_RATING_NAME + " REAL, " +
                LocationConstant.AVERAGE_SERVED_TIME_NAME + " TEXT, " +
                LocationConstant.MAIN_IMAGE_NAME + " TEXT);";

        sqLiteDatabase.execSQL(CREATE_TABLE_QUERY);
    }

    public LocationDatabaseHandler(Context context) {
        super(context, DatabaseSupport.getDatabseName(), null, DatabaseSupport.getDatabaseVersion());
    }


    public int deleteLocation(int id, String TableName, String KeyID) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(LocationConstant.TABLE_NAME, LocationConstant.KEY_ID + " = ? ",
                    new String[]{String.valueOf(id)});

            //implement trigger to delete all id in other table here
            db.close();
            return 1;
        } catch (Error e) {
            Log.v(TAG_DELETE, "Cannot delete member");
            return 0;
        }

    }

    public ArrayList<Location> getLocations() {
        SQLiteDatabase db = getReadableDatabase();
        //String SELECT_QUERY = "SELECT * FROM " + MusicConstant.TABLE_NAME;

        Cursor LocationCursor = db.query(LocationConstant.TABLE_NAME,
                new String[]{LocationConstant.KEY_ID, LocationConstant.LOCATION_NAME, LocationConstant.ADDRESS_NAME,
                        LocationConstant.WIFI_NAME, LocationConstant.AVERAGE_PRICE_NAME,
                        LocationConstant.AVERAGE_USER_RATING_NAME, LocationConstant.AVERAGE_SERVED_TIME_NAME,
                        LocationConstant.MAIN_IMAGE_NAME},
                null, null, null, null, LocationConstant.LOCATION_NAME + " ASC");

        if (LocationCursor.moveToFirst()) {

            do {

                Location tmpLocation = new Location();

                tmpLocation.setName(LocationCursor.getString(LocationCursor.getColumnIndex(LocationConstant.LOCATION_NAME)));
                tmpLocation.setAddress(LocationCursor.getString(LocationCursor.getColumnIndex(LocationConstant.ADDRESS_NAME)));
                tmpLocation.setAveragePrice(LocationCursor.getFloat(LocationCursor.getColumnIndex(LocationConstant.AVERAGE_PRICE_NAME)));
                tmpLocation.setAverageServedTime(LocationCursor.getFloat(LocationCursor.getColumnIndex(LocationConstant.AVERAGE_SERVED_TIME_NAME)));
                tmpLocation.setAverageUserRating(LocationCursor.getFloat(LocationCursor.getColumnIndex(LocationConstant.AVERAGE_USER_RATING_NAME)));
                tmpLocation.setWifi(LocationCursor.getString(LocationCursor.getColumnIndex(LocationConstant.WIFI_NAME)));
                tmpLocation.setID(LocationCursor.getInt(LocationCursor.getColumnIndex(LocationConstant.KEY_ID)));
                tmpLocation.setLocationPicture(LocationCursor.getString(LocationCursor.getColumnIndex(LocationConstant.MAIN_IMAGE_NAME)));

                Locationsdb.add(tmpLocation);
            } while (LocationCursor.moveToNext());

        }

        LocationCursor.close();
        db.close();

        return Locationsdb;
    }

    public ArrayList<Location> getLocations(String SearchString) {
        SQLiteDatabase db = getReadableDatabase();
        //String SELECT_QUERY = "SELECT * FROM " + MusicConstant.TABLE_NAME;

        Cursor LocationCursor = db.query(LocationConstant.TABLE_NAME,
                new String[]{LocationConstant.KEY_ID, LocationConstant.LOCATION_NAME, LocationConstant.ADDRESS_NAME,
                        LocationConstant.WIFI_NAME, LocationConstant.AVERAGE_PRICE_NAME,
                        LocationConstant.AVERAGE_USER_RATING_NAME, LocationConstant.AVERAGE_SERVED_TIME_NAME,
                        LocationConstant.MAIN_IMAGE_NAME},
                LocationConstant.LOCATION_NAME + " LIKE " + "'%" + SearchString + "%'",
                null, null, null, LocationConstant.AVERAGE_USER_RATING_NAME + " ASC");

        if (LocationCursor.moveToFirst()) {

            do {

                Location tmpLocation = new Location();

                tmpLocation.setName(LocationCursor.getString(LocationCursor.getColumnIndex(LocationConstant.LOCATION_NAME)));
                tmpLocation.setAddress(LocationCursor.getString(LocationCursor.getColumnIndex(LocationConstant.ADDRESS_NAME)));
                tmpLocation.setAveragePrice(LocationCursor.getFloat(LocationCursor.getColumnIndex(LocationConstant.AVERAGE_PRICE_NAME)));
                tmpLocation.setAverageServedTime(LocationCursor.getFloat(LocationCursor.getColumnIndex(LocationConstant.AVERAGE_SERVED_TIME_NAME)));
                tmpLocation.setAverageUserRating(LocationCursor.getFloat(LocationCursor.getColumnIndex(LocationConstant.AVERAGE_USER_RATING_NAME)));
                tmpLocation.setWifi(LocationCursor.getString(LocationCursor.getColumnIndex(LocationConstant.WIFI_NAME)));
                tmpLocation.setID(LocationCursor.getInt(LocationCursor.getColumnIndex(LocationConstant.KEY_ID)));
                tmpLocation.setLocationPicture(LocationCursor.getString(LocationCursor.getColumnIndex(LocationConstant.MAIN_IMAGE_NAME)));

                Locationsdb.add(tmpLocation);
            } while (LocationCursor.moveToNext());

        }

        LocationCursor.close();
        db.close();

        return Locationsdb;


    }

    public Location getLocations(int ID) {

        SQLiteDatabase db = getReadableDatabase();
        //String SELECT_QUERY = "SELECT * FROM " + MusicConstant.TABLE_NAME;

        Cursor LocationCursor = db.query(LocationConstant.TABLE_NAME,
                new String[]{LocationConstant.KEY_ID, LocationConstant.LOCATION_NAME, LocationConstant.ADDRESS_NAME,
                        LocationConstant.WIFI_NAME, LocationConstant.AVERAGE_PRICE_NAME,
                        LocationConstant.AVERAGE_USER_RATING_NAME, LocationConstant.AVERAGE_SERVED_TIME_NAME,
                        LocationConstant.MAIN_IMAGE_NAME},
                LocationConstant.LOCATION_NAME + " = " + "'%" + ID + "%'",
                null, null, null, LocationConstant.AVERAGE_USER_RATING_NAME + " ASC");

        if (LocationCursor.moveToFirst()) {

            do {

                Location tmpLocation = new Location();

                tmpLocation.setName(LocationCursor.getString(LocationCursor.getColumnIndex(LocationConstant.LOCATION_NAME)));
                tmpLocation.setAddress(LocationCursor.getString(LocationCursor.getColumnIndex(LocationConstant.ADDRESS_NAME)));
                tmpLocation.setAveragePrice(LocationCursor.getFloat(LocationCursor.getColumnIndex(LocationConstant.AVERAGE_PRICE_NAME)));
                tmpLocation.setAverageServedTime(LocationCursor.getFloat(LocationCursor.getColumnIndex(LocationConstant.AVERAGE_SERVED_TIME_NAME)));
                tmpLocation.setAverageUserRating(LocationCursor.getFloat(LocationCursor.getColumnIndex(LocationConstant.AVERAGE_USER_RATING_NAME)));
                tmpLocation.setWifi(LocationCursor.getString(LocationCursor.getColumnIndex(LocationConstant.WIFI_NAME)));
                tmpLocation.setID(LocationCursor.getInt(LocationCursor.getColumnIndex(LocationConstant.KEY_ID)));
                tmpLocation.setLocationPicture(LocationCursor.getString(LocationCursor.getColumnIndex(LocationConstant.MAIN_IMAGE_NAME)));

                Locationsdb.add(tmpLocation);
            } while (LocationCursor.moveToNext());

        }

        LocationCursor.close();
        db.close();

        return Locationsdb.get(0);
    }

}
