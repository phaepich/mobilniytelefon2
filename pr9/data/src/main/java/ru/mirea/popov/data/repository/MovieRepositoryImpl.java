package ru.mirea.popov.data.repository;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import ru.mirea.popov.domain.models.Movie;
import ru.mirea.popov.domain.repository.MovieRepository;

public class MovieRepositoryImpl implements MovieRepository {

    private final Context context;

    public MovieRepositoryImpl(Context context) {
        this.context = context;
    }

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Начало", "Фильм о снах и подсознании.", "inception"));
        movies.add(new Movie("Интерстеллар", "Космос, любовь и выживание человечества.", "interstellar"));
        movies.add(new Movie("Матрица", "Культовый киберпанк 1999 года.", "matrix"));
        movies.add(new Movie("Побег из Шоушенка", "История надежды и свободы.", "shawshank"));
        movies.add(new Movie("Форрест Гамп", "Жизнь как коробка конфет.", "forrest"));
        return movies;
    }
}
