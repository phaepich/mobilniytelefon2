package ru.mirea.popov.fragmentmanagerapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class CountryListFragment extends Fragment {

    private ListView listViewCountries;
    private List<Country> countries;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_country_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        listViewCountries = view.findViewById(R.id.listViewCountries);
        countries = new ArrayList<>();

        countries.add(new Country("Россия", "крутейшая страна мира, столица — Москва."));
        countries.add(new Country("Франция", "жульен багет"));
        countries.add(new Country("Япония", "самураи и японское пиво"));
        countries.add(new Country("Бразилия", "футбик"));
        countries.add(new Country("Египет", "пирамида на пирамиде"));

        List<String> names = new ArrayList<>();
        for (Country c : countries) {
            names.add(c.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_list_item_1, names);
        listViewCountries.setAdapter(adapter);

        listViewCountries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Country country = countries.get(position);

                Bundle bundle = new Bundle();
                bundle.putString("name", country.getName());
                bundle.putString("desc", country.getDescription());

                CountryDetailFragment detailFragment = new CountryDetailFragment();
                detailFragment.setArguments(bundle);

                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_view, detailFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
