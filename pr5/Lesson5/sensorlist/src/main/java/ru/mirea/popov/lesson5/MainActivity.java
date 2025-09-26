package ru.mirea.popov.lesson5;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ru.mirea.popov.lesson5.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        ListView sensorListView = binding.sensorListView;

        ArrayList<HashMap<String, Object>> sensorData = new ArrayList<>();
        for (Sensor sensor : sensors) {
            HashMap<String, Object> item = new HashMap<>();
            item.put("Name", sensor.getName());
            item.put("Value", sensor.getMaximumRange());
            sensorData.add(item);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, sensorData,
                android.R.layout.simple_list_item_2,
                new String[]{"Name", "Value"},
                new int[]{android.R.id.text1, android.R.id.text2});
        sensorListView.setAdapter(adapter);
    }
}
