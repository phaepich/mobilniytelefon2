package ru.mirea.popov.domain.usecases;

import ru.mirea.popov.domain.models.WeatherInfo;
import ru.mirea.popov.domain.repository.WeatherRepository;

public class GetWeatherUseCase {
    private final WeatherRepository repository;

    public GetWeatherUseCase(WeatherRepository repository) {
        this.repository = repository;
    }

    public WeatherInfo execute(String city) {
        return repository.getWeather(city);
    }
}
