package com.tata.diangi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;

import constant.LocationConstant;
import data.CityPreference;
import data.JSONWeatherParser;
import data.Utils;
import data.WeatherHttpClient;
import model.Location;
import model.Weather;

public class LocationDetailActivity extends AppCompatActivity {

    private ViewHolder view = new ViewHolder();
    private Weather currentWeather = new Weather();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        int itemId = getIDFromDisplayActivity();
        String type = determineLocationType(itemId);
        getCurrentViewID();

        ShowInformation(itemId, type);

        final ViewHolder fview = view;
        view.mAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent StartMap = new Intent(LocationDetailActivity.this, MapsActivity.class);
                StartMap.putExtra("SearchString", fview.mAddress.getText().toString() );
                startActivity(StartMap);*/
                String url = "http://maps.google.com/maps?daddr="+fview.mAddress.getText().toString();
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,  Uri.parse(url));
                startActivity(intent);

            }
        });
    }

    private String determineLocationType(int itemId) {
        return "";
    }

    private int getIDFromDisplayActivity() {
        try {
            Intent data;
            data = getIntent();

            return data.getIntExtra("ID", 0);
        } catch (Error e) {
            return Integer.parseInt(null);
        }
    }

    private void getCurrentViewID() {

        view.mCityName = (TextView) findViewById(R.id.content_location_detail_City);
        view.mTemp = (TextView) findViewById(R.id.content_location_detail_TempText);
        view.mDescription = (TextView) findViewById(R.id.content_location_detail_CloudText);
        view.mHumidity = (TextView) findViewById(R.id.content_location_detail_HumidText);
        view.mIconView = (ImageView) findViewById(R.id.content_location_detail_Icon);

        view.mName = (TextView) findViewById(R.id.content_location_detail_row_location_name);
        view.mAddress = (TextView) findViewById(R.id.content_location_detail_row_location_address);
        view.mPrice = (TextView) findViewById(R.id.content_location_detail_row_location_price);
        view.mRating = (TextView) findViewById(R.id.content_location_detail_row_location_rating);
        view.mThumbnail = (ImageView) findViewById(R.id.content_location_detail_row_thumbnail);

    }

    private void ShowInformation(int ItemId, String DataType) {
        String CityName = chooseCity();
        displayWeather(CityName);
        displayInformation(ItemId);
    }

    private void displayInformation(int itemId) {
        Location loc = getLocation(itemId);
        view.mName.setText(loc.getName());
        view.mAddress.setText(loc.getAddress());
        view.mPrice.setText("Giá cả: " + (int) loc.getAveragePrice());
        view.mRating.setText("Đánh giá: " + (int) loc.getAverageUserRating());
        view.mThumbnail.setImageBitmap(downloadImage2(loc.getLocationPicture()));
    }

    private Location getLocation(int itemId) {
        try {
            Intent data;
            Location loc = new Location();
            data = getIntent();

            loc.setID(data.getIntExtra("ID", 0));
            loc.setAverageUserRating(data.getFloatExtra("Rating", 0));
            loc.setName(data.getStringExtra("Name"));
            loc.setAddress(data.getStringExtra("Address"));
            loc.setAveragePrice(data.getFloatExtra("Price", 0));
            loc.setLocationPicture(data.getStringExtra("Thumbnail"));

            return loc;
        } catch (Error e) {
            return null;
        }
    }

    private void displayWeather(String cityName) {
        CityPreference cityPref = new CityPreference(LocationDetailActivity.this);
        renderWeatherData(cityPref.getCity());
    }

    public void renderWeatherData(String city) {
        WeatherTask weatherTask = new WeatherTask();
        weatherTask.execute(new String[]{city + "&units=metric"});
    }

    private class DownloadImageAsyncTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPostExecute(Bitmap bitmap) {

            super.onPostExecute(bitmap); //what does this do?
            view.mIconView.setImageBitmap(bitmap);

        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            return downloadImage(strings[0]);
        }
        @Nullable
        private Bitmap downloadImage(String code) {

            try {
                HttpURLConnection client = null;
                client = (HttpURLConnection) (new URL(Utils.ICON_URL + code + ".png")).openConnection();
                client.connect();

                final int statusCode = client.getResponseCode();

                if (statusCode == 200) {

                    InputStream imageStr = client.getInputStream();
                    Bitmap photo = BitmapFactory.decodeStream(imageStr);

                    return photo;

                } else {

                    Toast.makeText(LocationDetailActivity.this, "Can't fetch weather icon", Toast.LENGTH_SHORT).show();
                    return null;

                }


            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(LocationDetailActivity.this, "Can't fetch weather icon", Toast.LENGTH_SHORT).show();
            }

            return null;


        }



    }

    private class WeatherTask extends AsyncTask<String, Void, Weather> {
        @Override
        protected Weather doInBackground(String... strings) {

            String data = ((new WeatherHttpClient()).getWeatherData(strings[0]));

            currentWeather = JSONWeatherParser.getWeather(data);

            return currentWeather;
        }

        @Override
        protected void onPostExecute(Weather weather) {

            super.onPostExecute(weather);


            weather.iconData = weather.currentCondition.getIcon();

            new DownloadImageAsyncTask().execute(weather.iconData);

            DateFormat dateFormat = DateFormat.getTimeInstance();
            String sunriseDate = dateFormat.format(new Date(weather.place.getSunrise()));
            String sunsetDate = dateFormat.format(new Date(weather.place.getSunset()));

            DecimalFormat decimalFormat = new DecimalFormat("#.#");
            String tempFormat = decimalFormat.format(weather.currentCondition.getTemperature());

            view.mCityName.setText(weather.place.getCity() + ", " + weather.place.getCountry());
            view.mTemp.setText("" + tempFormat + "C");
            view.mHumidity.setText("Ẩm ướt: " + weather.currentCondition.getHumidity() + "%");
            view.mDescription.setText("Mây: " + weather.currentCondition.getCondition() + "( " +
                    weather.currentCondition.getDescription() + " )");

        }
    }
    private String chooseCity() {
        return "HoChiMinh,vn";
    }

    class ViewHolder {
        TextView mCityName;
        TextView mTemp;
        TextView mDescription;
        TextView mHumidity;
        TextView mPressure;
        TextView mSunrise;
        TextView mSunset;
        TextView mName;
        TextView mAddress;
        ImageView mThumbnail;
        TextView mPrice;
        TextView mRating;
        Location mLocation;
        ImageView mIconView;
        int mID;
    }
    @Nullable
    private Bitmap downloadImage2(String code) {

        try {
            HttpURLConnection client = null;
            client = (HttpURLConnection) (new URL(code)).openConnection();
            client.connect();

            final int statusCode = client.getResponseCode();

            if (statusCode == 200) {

                InputStream imageStr = client.getInputStream();
                Bitmap photo = BitmapFactory.decodeStream(imageStr);

                return photo;

            } else {

                Toast.makeText(LocationDetailActivity.this, "Can't fetch weather icon", Toast.LENGTH_SHORT).show();
                return null;

            }


        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(LocationDetailActivity.this, "Can't fetch weather icon", Toast.LENGTH_SHORT).show();
        }

        return null;


    }
}
