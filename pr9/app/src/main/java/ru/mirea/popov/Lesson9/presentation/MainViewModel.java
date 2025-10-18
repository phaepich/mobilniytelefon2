package ru.mirea.popov.Lesson9.presentation;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.util.Log;

import ru.mirea.popov.domain.models.Movie;
import ru.mirea.popov.domain.repository.MovieRepository;
import ru.mirea.popov.domain.usecases.GetFavoriteFilmUseCase;
import ru.mirea.popov.domain.usecases.SaveMovieToFavoriteUseCase;

public class MainViewModel extends ViewModel {
    private final MovieRepository movieRepository;
    private final MutableLiveData<String> favoriteMovie = new MutableLiveData<>();

    public MainViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
        Log.d("MainViewModel", "ViewModel создана");
    }

    public MutableLiveData<String> getFavoriteMovie() {
        return favoriteMovie;
    }

    public void saveMovie(Movie movie) {
        boolean result = new SaveMovieToFavoriteUseCase(movieRepository).execute(movie);
        if (result) {
            favoriteMovie.setValue("сохранено: " + movie.getName());
        }
    }

    public void loadMovie() {
        Movie movie = new GetFavoriteFilmUseCase(movieRepository).execute();
        favoriteMovie.setValue("как же я его обожаю: " + movie.getName());
    }

    @Override
    protected void onCleared() {
        Log.d("MainViewModel", "ViewModel очищена");
        super.onCleared();
    }
}
