package ru.mirea.popov.httpurlconnection;

import androidx.appcompat.app.AppCompatActivity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView ipTextView, cityTextView, regionTextView, countryTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ipTextView = findViewById(R.id.textViewIp);
        cityTextView = findViewById(R.id.textViewCity);
        regionTextView = findViewById(R.id.textViewRegion);
        countryTextView = findViewById(R.id.textViewCountry);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isConnected()) {
                new DownloadPageTask().execute("https://ipinfo.io/json");
            } else {
                Toast.makeText(this, "Нет интернета", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class DownloadPageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                return downloadIpInfo(urls[0]);
            } catch (Exception e) {
                e.printStackTrace();
                return "error";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject responseJson = new JSONObject(result);
                ipTextView.setText("IP: " + responseJson.getString("ip"));
                cityTextView.setText("City: " + responseJson.getString("city"));
                regionTextView.setText("Region: " + responseJson.getString("region"));
                countryTextView.setText("Country: " + responseJson.getString("country"));
            } catch (Exception e) {
                e.printStackTrace();
                ipTextView.setText("Ошибка разбора данных");
            }
        }
    }

    private String downloadIpInfo(String address) throws Exception {
        InputStream inputStream = null;
        String data = "";

        URL url = new URL(address);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setReadTimeout(10000);
        connection.setConnectTimeout(10000);
        connection.setRequestMethod("GET");
        connection.setDoInput(true);

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            inputStream = connection.getInputStream();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int read;
            while ((read = inputStream.read()) != -1) {
                bos.write(read);
            }
            bos.close();
            data = bos.toString();
        } else {
            data = connection.getResponseMessage() + ". Error Code: " + responseCode;
        }

        connection.disconnect();
        if (inputStream != null) inputStream.close();
        return data;
    }
}
