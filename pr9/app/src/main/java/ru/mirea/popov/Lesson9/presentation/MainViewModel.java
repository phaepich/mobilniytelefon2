package ru.mirea.popov.Lesson9.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;
import ru.mirea.popov.domain.models.Movie;
import ru.mirea.popov.domain.repository.MovieRepository;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<List<Movie>> moviesLiveData = new MutableLiveData<>();

    public MainViewModel(MovieRepository repository) {
        moviesLiveData.setValue(repository.getAllMovies());
    }

    public LiveData<List<Movie>> getMovies() {
        return moviesLiveData;
    }
}
