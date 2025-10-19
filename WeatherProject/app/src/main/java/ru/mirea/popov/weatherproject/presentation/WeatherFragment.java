package ru.mirea.popov.weatherproject.presentation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.squareup.picasso.Picasso;

import java.time.LocalTime;

import ru.mirea.popov.domain.models.WeatherInfo;
import ru.mirea.popov.weatherproject.R;

public class WeatherFragment extends Fragment {
    private WeatherViewModel vm;
    private TextView textCity, textTemp, textDesc, textUpdated;
    private ProgressBar progressBar;
    private Button buttonRefresh, buttonHistory;
    private ImageView imageWeatherIcon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        imageWeatherIcon = view.findViewById(R.id.imageWeatherIcon);
        textCity = view.findViewById(R.id.textCity);
        textTemp = view.findViewById(R.id.textTemp);
        textDesc = view.findViewById(R.id.textDesc);
        textUpdated = view.findViewById(R.id.textUpdated);
        progressBar = view.findViewById(R.id.progressBar);
        buttonRefresh = view.findViewById(R.id.buttonRefresh);
        buttonHistory = view.findViewById(R.id.buttonHistory);

        vm = new ViewModelProvider(requireActivity(), new WeatherViewModelFactory(requireContext())).get(WeatherViewModel.class);

        vm.getWeather().observe(getViewLifecycleOwner(), this::updateUI);
        vm.getLoading().observe(getViewLifecycleOwner(), isLoading -> {
            progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            buttonRefresh.setEnabled(!isLoading);
        });

        buttonRefresh.setOnClickListener(v -> {
            Log.d("WeatherAPI", "нажата кнопка обновить");
            vm.loadWeatherFromApi("Москва");
        });
        buttonHistory.setOnClickListener(v -> openHistory());

        return view;
    }

    private void updateUI(WeatherInfo info) {
        if (info == null) return;
        textCity.setText(info.getCity());
        textTemp.setText(String.format("%.1f°C", info.getTemperature()));
        textDesc.setText(info.getDescription());
        if (info.getIconCode() != null && !info.getIconCode().isEmpty()) {
            String url = "https://openweathermap.org/img/wn/" + info.getIconCode() + "@2x.png";
            Picasso.get().load(url).into(imageWeatherIcon);
        }
        textUpdated.setText("последнее обновление: " + LocalTime.now().withNano(0));
    }

    private void openHistory() {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, new HistoryFragment())
                .addToBackStack(null)
                .commit();
    }
}
