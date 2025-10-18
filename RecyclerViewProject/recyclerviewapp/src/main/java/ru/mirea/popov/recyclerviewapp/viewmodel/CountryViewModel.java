package ru.mirea.popov.recyclerviewapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ru.mirea.popov.recyclerviewapp.model.Country;
import ru.mirea.popov.recyclerviewapp.repository.CountryRepository;

public class CountryViewModel extends ViewModel {
    private final CountryRepository repository = new CountryRepository();

    public LiveData<List<Country>> getCountries() {
        return repository.getCountries();
    }
}
