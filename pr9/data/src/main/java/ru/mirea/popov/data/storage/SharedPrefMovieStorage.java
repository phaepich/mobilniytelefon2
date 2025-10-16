package ru.mirea.popov.data.storage;

import android.content.Context;
import android.content.SharedPreferences;

import java.time.LocalDate;

import ru.mirea.popov.data.storage.models.Movie;

public class SharedPrefMovieStorage implements MovieStorage {
    private static final String SHARED_PREFS_NAME = "shared_prefs_movie";
    private static final String KEY = "movie_name";
    private static final String DATE_KEY = "movie_date";
    private static final String ID_KEY = "movie_id";

    private final SharedPreferences sharedPreferences;

    public SharedPrefMovieStorage(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public Movie get() {
        String name = sharedPreferences.getString(KEY, "unknown");
        String date = sharedPreferences.getString(DATE_KEY, LocalDate.now().toString());
        int id = sharedPreferences.getInt(ID_KEY, 0);
        return new Movie(id, name, date);
    }

    @Override
    public boolean save(Movie movie) {
        sharedPreferences.edit()
                .putString(KEY, movie.getName())
                .putString(DATE_KEY, LocalDate.now().toString())
                .putInt(ID_KEY, movie.getId())
                .apply();
        return true;
    }
}
