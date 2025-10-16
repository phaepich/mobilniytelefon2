package ru.mirea.popov.domain.repository;

import ru.mirea.popov.domain.models.Movie;

public interface MovieRepository {
    boolean saveMovie(Movie movie);
    Movie getMovie();
}
