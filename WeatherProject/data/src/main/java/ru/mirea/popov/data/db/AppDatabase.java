package ru.mirea.popov.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import ru.mirea.popov.data.storage.RoomWeatherDao;

@Database(entities = {WeatherEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RoomWeatherDao weatherDao();
}
