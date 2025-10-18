package ru.mirea.popov.Lesson9.presentation;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.popov.Lesson9.R;
import ru.mirea.popov.data.repository.MovieRepositoryImpl;
import ru.mirea.popov.data.storage.SharedPrefMovieStorage;
import ru.mirea.popov.domain.models.Movie;
import ru.mirea.popov.domain.usecases.GetFavoriteFilmUseCase;
import ru.mirea.popov.domain.usecases.SaveMovieToFavoriteUseCase;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;
    private EditText editTextMovie;
    private TextView textViewMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextMovie = findViewById(R.id.editTextMovie);
        textViewMovie = findViewById(R.id.textViewMovie);
        Button buttonSave = findViewById(R.id.buttonSaveMovie);
        Button buttonGet = findViewById(R.id.buttonGetMovie);

        mainViewModel = new ViewModelProvider(this, new ViewModelFactory(this))
                .get(MainViewModel.class);

        mainViewModel.getFavoriteMovie().observe(this, text -> textViewMovie.setText(text));

        buttonSave.setOnClickListener(v -> {
            String name = editTextMovie.getText().toString();
            mainViewModel.saveMovie(new Movie(1, name));
        });

        buttonGet.setOnClickListener(v -> mainViewModel.loadMovie());
    }
}
