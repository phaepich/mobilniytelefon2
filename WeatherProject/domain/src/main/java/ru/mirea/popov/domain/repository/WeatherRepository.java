package ru.mirea.popov.domain.repository;

import ru.mirea.popov.domain.models.WeatherInfo;

public interface WeatherRepository {
    WeatherInfo getWeather(String city);
    void saveFavoriteCity(String city);
}
