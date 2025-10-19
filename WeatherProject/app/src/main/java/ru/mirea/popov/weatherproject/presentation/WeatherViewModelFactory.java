package ru.mirea.popov.weatherproject.presentation;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.popov.data.network.WeatherApi;
import ru.mirea.popov.data.network.WeatherNetworkSource;
import ru.mirea.popov.data.repository.WeatherRepositoryImpl;
import ru.mirea.popov.data.storage.PreferencesStorage;
import ru.mirea.popov.domain.usecases.GetWeatherUseCase;
import ru.mirea.popov.domain.usecases.SaveFavoriteCityUseCase;

public class WeatherViewModelFactory implements ViewModelProvider.Factory {
    private final Context context;

    public WeatherViewModelFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        PreferencesStorage prefs = new PreferencesStorage(context);
        WeatherApi api = WeatherNetworkSource.api();
        WeatherRepositoryImpl repo = new WeatherRepositoryImpl(context, api, prefs);
        return (T) new WeatherViewModel(new GetWeatherUseCase(repo), new SaveFavoriteCityUseCase(repo), repo);
    }
}
