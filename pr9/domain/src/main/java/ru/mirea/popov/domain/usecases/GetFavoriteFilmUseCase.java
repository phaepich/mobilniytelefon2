package ru.mirea.popov.domain.usecases;

import java.util.List;
import ru.mirea.popov.domain.models.Movie;
import ru.mirea.popov.domain.repository.MovieRepository;

public class GetFavoriteFilmUseCase {
    private final MovieRepository repository;

    public GetFavoriteFilmUseCase(MovieRepository repository) {
        this.repository = repository;
    }

    public List<Movie> execute() {
        return repository.getAllMovies();
    }
}
