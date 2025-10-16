package ru.mirea.popov.data.repository;

import ru.mirea.popov.domain.models.Movie;
import ru.mirea.popov.domain.repository.MovieRepository;

public class MovieRepositoryImpl implements MovieRepository {
    private Movie movie;

    @Override
    public boolean saveMovie(Movie movie) {
        this.movie = movie;
        return true;
    }

    @Override
    public Movie getMovie() {
        if (movie == null) {
            return new Movie(1, "дефолтный фильм");
        }
        return movie;
    }
}
