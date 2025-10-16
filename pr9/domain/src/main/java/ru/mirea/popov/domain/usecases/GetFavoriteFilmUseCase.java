package ru.mirea.popov.domain.usecases;

import ru.mirea.popov.domain.models.Movie;
import ru.mirea.popov.domain.repository.MovieRepository;

public class GetFavoriteFilmUseCase {
    private final MovieRepository movieRepository;

    public GetFavoriteFilmUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie execute() {
        return movieRepository.getMovie();
    }
}
