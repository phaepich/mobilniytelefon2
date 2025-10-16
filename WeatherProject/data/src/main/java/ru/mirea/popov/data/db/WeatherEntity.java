package ru.mirea.popov.data.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "weather_history")
public class WeatherEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String city;
    public double temperature;
    public String description;
    public String date;

    public WeatherEntity(String city, double temperature, String description, String date) {
        this.city = city;
        this.temperature = temperature;
        this.description = description;
        this.date = date;
    }
}
