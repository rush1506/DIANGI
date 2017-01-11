package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by vutha_000 on 1/5/2017.
 */
public class WeatherHttpClient {

    public  String getWeatherData (String place) {

        HttpURLConnection connection = null;
        InputStream inputStream = null;

        try {
            place = "1566083";
            //connection = (HttpURLConnection) (new URL(Utils.BASE_URL + place + "&appid=88e229f4f2f434bbe504761f58b5058f")).openConnection();
            connection = (HttpURLConnection) (new URL(Utils.BASE_URL + place + "&appid=88e229f4f2f434bbe504761f58b5058f")).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true); //??
                connection.connect();

            //Read response
            StringBuffer stringBuffer = new StringBuffer();
            inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {

                stringBuffer.append(line + "\r\n");
            }

            inputStream.close();
            connection.disconnect();

            return stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
