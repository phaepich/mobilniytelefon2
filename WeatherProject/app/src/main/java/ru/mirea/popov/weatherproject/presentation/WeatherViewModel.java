package ru.mirea.popov.weatherproject.presentation;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ru.mirea.popov.data.db.WeatherEntity;
import ru.mirea.popov.data.repository.WeatherRepositoryImpl;
import ru.mirea.popov.domain.models.WeatherInfo;
import ru.mirea.popov.domain.usecases.GetWeatherUseCase;
import ru.mirea.popov.domain.usecases.SaveFavoriteCityUseCase;

public class WeatherViewModel extends ViewModel {
    private final GetWeatherUseCase getWeatherUseCase;
    private final SaveFavoriteCityUseCase saveFavoriteCityUseCase;
    private final WeatherRepositoryImpl repository;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    private final MutableLiveData<Boolean> loading = new MutableLiveData<>(false);
    private final MediatorLiveData<WeatherInfo> weather = new MediatorLiveData<>();

    public WeatherViewModel(GetWeatherUseCase getWeatherUseCase, SaveFavoriteCityUseCase saveFavoriteCityUseCase, WeatherRepositoryImpl repository) {
        this.getWeatherUseCase = getWeatherUseCase;
        this.saveFavoriteCityUseCase = saveFavoriteCityUseCase;
        this.repository = repository;
    }

    public MutableLiveData<Boolean> getLoading() { return loading; }
    public MediatorLiveData<WeatherInfo> getWeather() { return weather; }
    public LiveData<List<WeatherEntity>> getWeatherHistory() { return repository.getAllWeatherLive(); }

    public void loadWeatherFromApi(String city) {
        loading.postValue(true);
        Log.d("WeatherAPI", "ViewModel: запускаю executor для " + city);
        executor.execute(() -> {
            Log.d("WeatherAPI", "Executor: начал выполнение запроса");
            WeatherInfo info = repository.fetchWeatherFromApi(city);
            Log.d("WeatherAPI", "Executor: запрос завершён");
            weather.postValue(info);
            saveFavoriteCityUseCase.execute(city);
            loading.postValue(false);
        });
    }
}
