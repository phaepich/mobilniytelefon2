package ru.mirea.popov.data.storage;

import ru.mirea.popov.data.storage.models.Movie;

public interface MovieStorage {
    Movie get();
    boolean save(Movie movie);
}
