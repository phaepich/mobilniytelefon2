package ru.mirea.popov.recyclerviewapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.popov.recyclerviewapp.model.Country;

public class CountryRepository {
    private final MutableLiveData<List<Country>> countriesLiveData = new MutableLiveData<>();

    public CountryRepository() {
        List<Country> countries = new ArrayList<>();
        countries.add(new Country("Россия", "flag_russia", 146000000));
        countries.add(new Country("Китай", "flag_china", 1420000000));
        countries.add(new Country("Индия", "flag_india", 1430000000));
        countries.add(new Country("Бразилия", "flag_brazil", 214000000));
        countries.add(new Country("ЮАР", "flag_south_africa", 60000000));
        countriesLiveData.setValue(countries);
    }

    public LiveData<List<Country>> getCountries() {
        return countriesLiveData;
    }
}
