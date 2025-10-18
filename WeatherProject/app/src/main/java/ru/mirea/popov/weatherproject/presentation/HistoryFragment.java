package ru.mirea.popov.weatherproject.presentation;

import android.os.Bundle;
import android.view.*;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import ru.mirea.popov.data.db.WeatherEntity;
import ru.mirea.popov.weatherproject.R;

public class HistoryFragment extends Fragment {
    private WeatherViewModel vm;
    private WeatherHistoryAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerHistory);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new WeatherHistoryAdapter();
        recyclerView.setAdapter(adapter);

        vm = new ViewModelProvider(requireActivity(), new WeatherViewModelFactory(requireContext()))
                .get(WeatherViewModel.class);

        vm.getWeatherHistory().observe(getViewLifecycleOwner(), this::updateList);

        return view;
    }

    private void updateList(List<WeatherEntity> list) {
        adapter.setItems(list);
    }
}
