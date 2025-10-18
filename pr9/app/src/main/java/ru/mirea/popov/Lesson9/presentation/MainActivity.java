package ru.mirea.popov.Lesson9.presentation;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ru.mirea.popov.Lesson9.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewMovies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MovieAdapter adapter = new MovieAdapter();
        recyclerView.setAdapter(adapter);

        MainViewModel viewModel = new ViewModelProvider(this, new ViewModelFactory(this))
                .get(MainViewModel.class);

        viewModel.getMovies().observe(this, adapter::setItems);
    }
}
