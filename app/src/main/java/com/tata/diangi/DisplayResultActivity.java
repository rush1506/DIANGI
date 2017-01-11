package com.tata.diangi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import data.Utils;
import model.Location;

import static data.DatabaseSupport.searchForPlaceFromDatabase;

public class DisplayResultActivity extends AppCompatActivity {

    private EditText SearchBox; //Secondary search box, on the display result task bar.
    private ListView LocationsListview;
    private Handler searchHandler = new Handler(); //This Handle will handle this job: update display search result continuously.
    private LocationAdapter locationAdapter;
    ArrayList<Location> LocationsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_result);
        SearchBox = (EditText) findViewById(R.id.activity_display_result_SearchBox);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //Happe uppon created: When user enter the search string from the search activity/
        String SearchString; //Search String from the simple search activity
        SearchString = getSearchStringFromSearchActivity();
        //Because there're too many data table, we will only return the match data's ID array
        LocationsList = searchForPlaceFromDatabase(SearchString, getApplicationContext());

        DisplaySearchResult(LocationsList);
        //Set the searchbox for continuous watch, and update the search list frequently
        //searchHandler.post(UpdateSearchList);
        SearchBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                //The first update will only be triggered when the user click the search box on the task bar for the first time.
                if (i == EditorInfo.IME_NULL
                        && keyEvent.getAction() == KeyEvent.ACTION_DOWN)  {
                    searchHandler.post(UpdateSearchList);
                }
                return true;
            }
        });

    }

    private void DisplaySearchResult(ArrayList<Location> locationsList) {
        //set Adapter
        LocationsListview = (ListView) findViewById(R.id.content_display_result_listview);
        locationAdapter = new LocationAdapter(DisplayResultActivity.this, R.layout.content_display_result_row, locationsList);
        LocationsListview.setAdapter(locationAdapter);
        locationAdapter.notifyDataSetChanged();

    }

    /**
     * This medthod will return either a string from the start up search activity or will return a null string if something wrong happened
     *@return    Search string from the simple search activity
    **/
    @Nullable
    private String getSearchStringFromSearchActivity() {
        try {
            Intent data;

            data = getIntent();
            String SearchString; //Search string that user input, this string can be anything, from address to food type

            SearchString = data.getStringExtra("SearchString");

            return SearchString;
        } catch (Error e) {
            return null;
        }
    }
    /**
     * This medthod is ran in background, after the user first input in the search post, it will update the search post automatically after every 1 secs
     */
    private Runnable UpdateSearchList = new Runnable() {
        @Override
        public void run() {
            String SearchString = SearchBox.getText().toString();

            LocationsList.clear();

            LocationsList = searchForPlaceFromDatabase(SearchString, getApplicationContext());

            DisplaySearchResult(LocationsList);
            //locationAdapter.notifyDataSetChanged();
            //Delay the update for 1 second
            searchHandler.postDelayed(this, 1000);
        }
    };

    private class LocationAdapter extends ArrayAdapter<Location>{

        Activity activity;
        int layoutRes;
        ArrayList<Location> mData;

        public LocationAdapter(Activity act, int resource, ArrayList<Location> data) {
            super(act, resource, data);
            activity = act;
            layoutRes = resource;
            mData = data;
        }

        @Nullable
        @Override
        public Location getItem(int position) {
            return mData.get(position);
        }

        @Override
        public int getPosition(Location item) {
            return super.getPosition(item);
        }

        @Override
        public long getItemId(int position) {
            return mData.get(position).getID();
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            ViewHolder holder = null;
            if (row == null || (row.getTag() == null)) {

                holder = new ViewHolder();
                LayoutInflater inf = LayoutInflater.from(activity);
                row = inf.inflate(layoutRes, null);

                holder.mName = (TextView) row.findViewById(R.id.content_display_resutl_row_location_name);
                holder.mAddress = (TextView) row.findViewById(R.id.content_display_resutl_row_location_address);
                holder.mPrice = (TextView) row.findViewById(R.id.content_display_resutl_row_location_price);
                holder.mRating = (TextView) row.findViewById(R.id.content_display_resutl_row_location_rating);
                holder.mThumbnail = (ImageView) row.findViewById(R.id.content_display_resutl_row_thumbnail);

                row.setTag(holder);

            }else {

                holder = (ViewHolder) row.getTag();
            }
            holder.mLocation = getItem(position);

            holder.mID = holder.mLocation.getID();
            holder.mName.setText(holder.mLocation.getName());
            holder.mAddress.setText(holder.mLocation.getAddress());
            holder.mPrice.setText("Giá cả: " + (int) holder.mLocation.getAveragePrice());
            holder.mRating.setText("Đánh giá: " + (int) holder.mLocation.getAverageUserRating());
            holder.mThumbnail.setImageBitmap(downloadImage(holder.mLocation.getLocationPicture()));

            final ViewHolder finalHolder = holder;

            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //start new intent with
                    Intent locationDetail = new Intent(DisplayResultActivity.this, LocationDetailActivity.class);
                    locationDetail.putExtra("ID", finalHolder.mID);
                    locationDetail.putExtra("Name", finalHolder.mLocation.getName());
                    locationDetail.putExtra("Address", finalHolder.mLocation.getAddress());
                    locationDetail.putExtra("Price", finalHolder.mLocation.getAveragePrice());
                    locationDetail.putExtra("Rating", finalHolder.mLocation.getAverageUserRating());
                    locationDetail.putExtra("Thumbnail", finalHolder.mLocation.getLocationPicture());

                    startActivity(locationDetail);
                }
            });

            return row;
        }

        class ViewHolder {

            TextView mName;
            TextView mAddress;
            ImageView mThumbnail;
            TextView mPrice;
            TextView mRating;
            Location mLocation;
            int mID;

        }
    }

    private Bitmap downloadImage(String code) {

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

                Toast.makeText(DisplayResultActivity.this, "Can't fetch weather icon", Toast.LENGTH_SHORT).show();
                return null;

            }


        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(DisplayResultActivity.this, "Can't fetch weather icon", Toast.LENGTH_SHORT).show();
        }

        return null;


    }
}

