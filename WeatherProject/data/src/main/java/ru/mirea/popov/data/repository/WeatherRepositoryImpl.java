package ru.mirea.popov.data.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import org.json.JSONObject;

import java.time.LocalDate;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import ru.mirea.popov.data.db.AppDatabase;
import ru.mirea.popov.data.db.WeatherEntity;
import ru.mirea.popov.data.network.WeatherApi;
import ru.mirea.popov.data.network.WeatherNetworkSource;
import ru.mirea.popov.data.network.models.WeatherResponse;
import ru.mirea.popov.data.storage.PreferencesStorage;
import ru.mirea.popov.domain.models.WeatherInfo;
import ru.mirea.popov.domain.repository.WeatherRepository;

public class WeatherRepositoryImpl implements WeatherRepository {
    private final PreferencesStorage prefs;
    private final AppDatabase db;

    public WeatherRepositoryImpl(Context context, ru.mirea.popov.data.network.WeatherApi unused, PreferencesStorage prefs) {
        this.prefs = prefs;
        this.db = Room.databaseBuilder(context, AppDatabase.class, "weather_db").fallbackToDestructiveMigration().build();
    }

    @Override
    public WeatherInfo getWeather(String city) {
        JSONObject json = null;
        return new WeatherInfo(city, 0, "недоступно(", "");
    }

    @Override
    public void saveFavoriteCity(String city) {
        prefs.saveCity(city);
    }

    public WeatherInfo fetchWeatherFromApi(String city) {
        try {
            Log.d("WeatherAPI", "fetchWeatherFromApi: старт запроса");
            WeatherApi api = WeatherNetworkSource.api();
            Call<WeatherResponse> call = api.getWeather(city, "24e284d429fb1602e2f1c492919f4ed3", "metric", "ru");
            Response<WeatherResponse> response = call.execute();
            Log.d("WeatherAPI", "fetchWeatherFromApi: ответ получен");

            if (response.isSuccessful() && response.body() != null) {
                WeatherResponse data = response.body();
                String desc = data.weather != null && data.weather.length > 0 ? data.weather[0].description : "";
                String icon = data.weather != null && data.weather.length > 0 ? data.weather[0].icon : "";
                double temp = data.main != null ? data.main.temp : 0.0;
                Log.d("WeatherAPI", "успешный ответ: " + desc + " (" + temp + "°C)");

                WeatherInfo info = new WeatherInfo(data.cityName, temp, desc, icon);
                db.weatherDao().insert(new WeatherEntity(info.getCity(), info.getTemperature(), info.getDescription(), LocalDate.now().toString(), info.getIconCode()));
                return info;
            } else {
                Log.e("WeatherAPI", "ошибка HTTP: " + response.code());
            }
        } catch (Exception e) {
            Log.e("WeatherAPI", "исключение в fetchWeatherFromApi()", e);
        }
        return new WeatherInfo(city, 0, "ошибка подключения", "");
    }


    public LiveData<List<WeatherEntity>> getAllWeatherLive() {
        return db.weatherDao().getAllWeatherLive();
    }
}
