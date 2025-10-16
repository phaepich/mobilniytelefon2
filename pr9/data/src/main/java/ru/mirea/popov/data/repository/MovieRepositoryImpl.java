package ru.mirea.popov.data.repository;

import ru.mirea.popov.data.storage.MovieStorage;
import ru.mirea.popov.data.storage.models.Movie;
import ru.mirea.popov.domain.repository.MovieRepository;

public class MovieRepositoryImpl implements MovieRepository {
    private final MovieStorage movieStorage;

    public MovieRepositoryImpl(MovieStorage movieStorage) {
        this.movieStorage = movieStorage;
    }

    @Override
    public boolean saveMovie(ru.mirea.popov.domain.models.Movie movie) {
        movieStorage.save(mapToStorage(movie));
        return true;
    }

    @Override
    public ru.mirea.popov.domain.models.Movie getMovie() {
        Movie movie = movieStorage.get();
        return mapToDomain(movie);
    }

    private Movie mapToStorage(ru.mirea.popov.domain.models.Movie movie) {
        return new Movie(movie.getId(), movie.getName(), "");
    }

    private ru.mirea.popov.domain.models.Movie mapToDomain(Movie movie) {
        return new ru.mirea.popov.domain.models.Movie(movie.getId(), movie.getName());
    }
}
