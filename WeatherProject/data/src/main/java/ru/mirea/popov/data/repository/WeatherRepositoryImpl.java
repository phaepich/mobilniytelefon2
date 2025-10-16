package ru.mirea.popov.data.repository;

import android.content.Context;
import androidx.room.Room;

import org.json.JSONObject;
import java.time.LocalDate;
import java.util.List;

import ru.mirea.popov.data.db.AppDatabase;
import ru.mirea.popov.data.db.WeatherEntity;
import ru.mirea.popov.data.network.WeatherApi;
import ru.mirea.popov.data.storage.PreferencesStorage;
import ru.mirea.popov.domain.models.WeatherInfo;
import ru.mirea.popov.domain.repository.WeatherRepository;

public class WeatherRepositoryImpl implements WeatherRepository {
    private final WeatherApi api;
    private final PreferencesStorage prefs;
    private final AppDatabase db;

    public WeatherRepositoryImpl(Context context, WeatherApi api, PreferencesStorage prefs) {
        this.api = api;
        this.prefs = prefs;
        this.db = Room.databaseBuilder(context, AppDatabase.class, "weather_db").build();
    }

    @Override
    public WeatherInfo getWeather(String city) {
        JSONObject json = api.getWeatherData(city);
        if (json == null) return new WeatherInfo(city, 0, "Ошибка сети");
        try {
            JSONObject current = json.getJSONObject("current_weather");
            double temp = current.getDouble("temperature");
            WeatherInfo info = new WeatherInfo(city, temp, "Погода получена");
            db.weatherDao().insert(new WeatherEntity(city, temp, info.getDescription(), LocalDate.now().toString()));
            return info;
        } catch (Exception e) {
            return new WeatherInfo(city, 0, "Ошибка парсинга");
        }
    }

    @Override
    public void saveFavoriteCity(String city) {
        prefs.saveCity(city);
    }

    // 👇 добавляем доступ к истории
    public List<WeatherEntity> getLastWeather() {
        return db.weatherDao().getLastWeather();
    }
}
