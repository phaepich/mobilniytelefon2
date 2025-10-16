package ru.mirea.popov.weatherproject.presentation;

import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.fragment.app.Fragment;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import android.os.Handler;
import android.os.Looper;

import ru.mirea.popov.data.db.WeatherEntity;
import ru.mirea.popov.data.network.WeatherApi;
import ru.mirea.popov.data.repository.WeatherRepositoryImpl;
import ru.mirea.popov.data.storage.PreferencesStorage;
import ru.mirea.popov.domain.usecases.GetWeatherUseCase;
import ru.mirea.popov.domain.usecases.SaveFavoriteCityUseCase;
import ru.mirea.popov.domain.models.WeatherInfo;
import ru.mirea.popov.weatherproject.R;

public class WeatherFragment extends Fragment {
    private TextView textCity, textTemp, textDesc, textHistory;
    private Button buttonRefresh, buttonHistory;
    private GetWeatherUseCase getWeatherUseCase;
    private SaveFavoriteCityUseCase saveFavoriteCityUseCase;
    private WeatherRepositoryImpl repository;
    private ExecutorService executor;
    private Handler mainHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        textCity = view.findViewById(R.id.textCity);
        textTemp = view.findViewById(R.id.textTemp);
        textDesc = view.findViewById(R.id.textDesc);
        textHistory = view.findViewById(R.id.textHistory);
        buttonRefresh = view.findViewById(R.id.buttonRefresh);
        buttonHistory = view.findViewById(R.id.buttonHistory);

        PreferencesStorage prefs = new PreferencesStorage(requireContext());
        repository = new WeatherRepositoryImpl(requireContext(), new WeatherApi(), prefs);
        getWeatherUseCase = new GetWeatherUseCase(repository);
        saveFavoriteCityUseCase = new SaveFavoriteCityUseCase(repository);

        executor = Executors.newSingleThreadExecutor();
        mainHandler = new Handler(Looper.getMainLooper());

        buttonRefresh.setOnClickListener(v -> updateWeather(prefs.getCity()));

        buttonHistory.setOnClickListener(v -> showHistory());

        return view;
    }

    private void updateWeather(String city) {
        textDesc.setText("Загружаем погоду...");

        executor.execute(() -> {
            WeatherInfo info = getWeatherUseCase.execute(city);
            mainHandler.post(() -> {
                textCity.setText(info.getCity());
                textTemp.setText("Температура: " + info.getTemperature() + "°C");
                textDesc.setText(info.getDescription());
                saveFavoriteCityUseCase.execute(info.getCity());
            });
        });
    }

    private void showHistory() {
        executor.execute(() -> {
            List<WeatherEntity> list = repository.getLastWeather();
            mainHandler.post(() -> {
                StringBuilder sb = new StringBuilder();
                for (WeatherEntity w : list) {
                    sb.append(w.city)
                            .append(": ").append(w.temperature)
                            .append("°C (").append(w.date).append(")\n");
                }
                textHistory.setText(sb.toString());
            });
        });
    }
}
