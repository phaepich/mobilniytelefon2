package ru.mirea.popov.Lesson9.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.popov.Lesson9.R;
import ru.mirea.popov.data.repository.MovieRepositoryImpl;
import ru.mirea.popov.domain.models.Movie;
import ru.mirea.popov.domain.repository.MovieRepository;
import ru.mirea.popov.domain.usecases.GetFavoriteFilmUseCase;
import ru.mirea.popov.domain.usecases.SaveMovieToFavoriteUseCase;

public class MainActivity extends AppCompatActivity {
    private MovieRepository movieRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieRepository = new MovieRepositoryImpl();

        EditText editText = findViewById(R.id.editTextMovie);
        Button buttonSave = findViewById(R.id.buttonSaveMovie);
        Button buttonGet = findViewById(R.id.buttonGetMovie);
        TextView textViewResult = findViewById(R.id.textViewResult);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String movieName = editText.getText().toString();
                boolean result = new SaveMovieToFavoriteUseCase(movieRepository)
                        .execute(new Movie(2, movieName));
                textViewResult.setText("сохранено: " + result);
            }
        });

        buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Movie movie = new GetFavoriteFilmUseCase(movieRepository).execute();
                textViewResult.setText("любимый фильм: " + movie.getName());
            }
        });
    }
}
