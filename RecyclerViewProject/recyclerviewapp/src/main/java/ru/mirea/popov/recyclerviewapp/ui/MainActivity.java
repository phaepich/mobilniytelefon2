package ru.mirea.popov.recyclerviewapp.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.mirea.popov.recyclerviewapp.R;
import ru.mirea.popov.recyclerviewapp.adapter.CountryAdapter;
import ru.mirea.popov.recyclerviewapp.viewmodel.CountryViewModel;

public class MainActivity extends AppCompatActivity {

    private CountryViewModel countryViewModel;
    private CountryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new CountryAdapter();
        recyclerView.setAdapter(adapter);

        countryViewModel = new ViewModelProvider(this).get(CountryViewModel.class);
        countryViewModel.getCountries().observe(this, adapter::setItems);
    }
}
