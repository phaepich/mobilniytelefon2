package ru.mirea.popov.data.network;

import android.util.Log;

import org.json.JSONObject;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherApi {
    public JSONObject getWeatherData(String city) {
        try {
            String urlStr = "https://api.open-meteo.com/v1/forecast?latitude=55.75&longitude=37.61&current_weather=true";
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream input = connection.getInputStream();
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            int b;
            while ((b = input.read()) != -1) {
                output.write(b);
            }

            String jsonString = output.toString();
            connection.disconnect();
            return new JSONObject(jsonString);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("WeatherApi", "Ошибка получения погоды", e);

            return null;
        }
    }
}
