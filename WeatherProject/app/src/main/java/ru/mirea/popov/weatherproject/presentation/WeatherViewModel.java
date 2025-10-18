package ru.mirea.popov.weatherproject.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import ru.mirea.popov.data.db.WeatherEntity;
import ru.mirea.popov.domain.models.WeatherInfo;
import ru.mirea.popov.domain.usecases.GetWeatherUseCase;
import ru.mirea.popov.domain.usecases.SaveFavoriteCityUseCase;
import ru.mirea.popov.data.repository.WeatherRepositoryImpl;

public class WeatherViewModel extends ViewModel {
    private final GetWeatherUseCase getWeatherUseCase;
    private final SaveFavoriteCityUseCase saveFavoriteCityUseCase;
    private final WeatherRepositoryImpl repository;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    private final MutableLiveData<Boolean> loading = new MutableLiveData<>(false);
    private final MediatorLiveData<WeatherInfo> weather = new MediatorLiveData<>();

    public WeatherViewModel(GetWeatherUseCase getWeatherUseCase,
                            SaveFavoriteCityUseCase saveFavoriteCityUseCase,
                            WeatherRepositoryImpl repository) {
        this.getWeatherUseCase = getWeatherUseCase;
        this.saveFavoriteCityUseCase = saveFavoriteCityUseCase;
        this.repository = repository;
    }

    public MutableLiveData<Boolean> getLoading() { return loading; }
    public MediatorLiveData<WeatherInfo> getWeather() { return weather; }

    public LiveData<List<WeatherEntity>> getWeatherHistory() {
        return repository.getAllWeatherLive();
    }

    public void loadWeather(String city) {
        loading.postValue(true);
        executor.execute(() -> {
            WeatherInfo info = getWeatherUseCase.execute(city);
            weather.postValue(info);
            saveFavoriteCityUseCase.execute(city);
            loading.postValue(false);
        });
    }
}
