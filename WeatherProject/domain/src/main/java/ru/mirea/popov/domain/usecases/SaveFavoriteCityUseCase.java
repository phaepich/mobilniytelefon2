package ru.mirea.popov.domain.usecases;

import ru.mirea.popov.domain.repository.WeatherRepository;

public class SaveFavoriteCityUseCase {
    private final WeatherRepository repository;

    public SaveFavoriteCityUseCase(WeatherRepository repository) {
        this.repository = repository;
    }

    public void execute(String city) {
        repository.saveFavoriteCity(city);
    }
}
