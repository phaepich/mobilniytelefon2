package ru.mirea.popov.Lesson9.domain.usecases;

import ru.mirea.popov.Lesson9.domain.models.Movie;
import ru.mirea.popov.Lesson9.domain.repository.MovieRepository;

public class SaveMovieToFavoriteUseCase {
    private final MovieRepository movieRepository;

    public SaveMovieToFavoriteUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public boolean execute(Movie movie) {
        return movieRepository.saveMovie(movie);
    }
}