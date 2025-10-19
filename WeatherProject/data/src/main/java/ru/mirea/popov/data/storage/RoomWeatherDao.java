package ru.mirea.popov.data.storage;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import ru.mirea.popov.data.db.WeatherEntity;

@Dao
public interface RoomWeatherDao {
    @Insert
    void insert(WeatherEntity weather);

    @Query("SELECT * FROM weather_history ORDER BY id DESC LIMIT 5")
    List<WeatherEntity> getLastWeather();

    @Query("SELECT * FROM weather_history ORDER BY id DESC")
    LiveData<List<WeatherEntity>> getAllWeatherLive();
}
