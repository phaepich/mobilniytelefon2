package ru.mirea.popov.Lesson9.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;
import ru.mirea.popov.data.repository.MovieRepositoryImpl;
import ru.mirea.popov.domain.models.Movie;
import ru.mirea.popov.domain.repository.MovieRepository;
import ru.mirea.popov.domain.usecases.GetFavoriteFilmUseCase;

public class MainViewModel extends ViewModel {
    private final MutableLiveData<List<Movie>> moviesLiveData = new MutableLiveData<>();

    public MainViewModel(MovieRepository repo) {
        loadMovies();
    }

    private void loadMovies() {
        GetFavoriteFilmUseCase useCase = new GetFavoriteFilmUseCase(new MovieRepositoryImpl());
        moviesLiveData.setValue(useCase.execute());
    }

    public LiveData<List<Movie>> getMovies() {
        return moviesLiveData;
    }
}
