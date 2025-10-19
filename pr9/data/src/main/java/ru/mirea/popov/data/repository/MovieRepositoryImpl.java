package ru.mirea.popov.data.repository;

import java.util.ArrayList;
import java.util.List;
import ru.mirea.popov.domain.models.Movie;
import ru.mirea.popov.domain.repository.MovieRepository;

public class MovieRepositoryImpl implements MovieRepository {

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Интерстеллар", "Фантастика", 2014));
        movies.add(new Movie("Паразиты", "Драма", 2019));
        movies.add(new Movie("Начало", "Триллер", 2010));
        movies.add(new Movie("Матрица", "Фантастика", 1999));
        movies.add(new Movie("Бойцовский клуб", "Драма", 1999));
        movies.add(new Movie("Гарри Поттер", "Фэнтези", 2001));
        movies.add(new Movie("Остров проклятых", "Детектив", 2010));
        movies.add(new Movie("Гладиатор", "Исторический боевик", 2000));
        movies.add(new Movie("Титаник", "Романтика", 1997));
        movies.add(new Movie("Тёмный рыцарь", "Боевик", 2008));
        return movies;
    }
    @Override
    public boolean saveMovie(Movie movie) {
        return true;
    }

    @Override
    public Movie getFavoriteMovie() {
        return new Movie("Интерстеллар", "Фантастика", 2014);
    }

}
