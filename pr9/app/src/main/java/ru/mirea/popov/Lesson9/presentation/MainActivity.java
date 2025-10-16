package ru.mirea.popov.Lesson9.presentation;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.popov.Lesson9.R;
import ru.mirea.popov.data.repository.MovieRepositoryImpl;
import ru.mirea.popov.data.storage.SharedPrefMovieStorage;
import ru.mirea.popov.domain.models.Movie;
import ru.mirea.popov.domain.usecases.GetFavoriteFilmUseCase;
import ru.mirea.popov.domain.usecases.SaveMovieToFavoriteUseCase;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextMovie = findViewById(R.id.editTextMovie);
        TextView textViewMovie = findViewById(R.id.textViewMovie);
        Button buttonSave = findViewById(R.id.buttonSaveMovie);
        Button buttonGet = findViewById(R.id.buttonGetMovie);

        SharedPrefMovieStorage storage = new SharedPrefMovieStorage(this);
        MovieRepositoryImpl repository = new MovieRepositoryImpl(storage);

        SaveMovieToFavoriteUseCase saveUseCase = new SaveMovieToFavoriteUseCase(repository);
        GetFavoriteFilmUseCase getUseCase = new GetFavoriteFilmUseCase(repository);

        buttonSave.setOnClickListener(v -> {
            boolean result = saveUseCase.execute(new Movie(1, editTextMovie.getText().toString()));
            textViewMovie.setText("сохранено: " + result);
        });

        buttonGet.setOnClickListener(v -> {
            Movie movie = getUseCase.execute();
            textViewMovie.setText("ииииз памяти: " + movie.getName());
        });
    }
}
