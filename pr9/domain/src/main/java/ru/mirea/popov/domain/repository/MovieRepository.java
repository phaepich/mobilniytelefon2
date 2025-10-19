package ru.mirea.popov.domain.repository;

import java.util.List;

import ru.mirea.popov.domain.models.Movie;

public interface MovieRepository {
    List<Movie> getAllMovies();

    boolean saveMovie(Movie movie);
    Movie getFavoriteMovie();
}


