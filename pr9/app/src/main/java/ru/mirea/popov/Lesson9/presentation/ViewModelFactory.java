package ru.mirea.popov.Lesson9.presentation;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.popov.data.repository.MovieRepositoryImpl;
import ru.mirea.popov.data.storage.SharedPrefMovieStorage;
import ru.mirea.popov.data.storage.MovieStorage;
import ru.mirea.popov.domain.repository.MovieRepository;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private final Context context;

    public ViewModelFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        MovieStorage storage = new SharedPrefMovieStorage(context);
        MovieRepository repo = new MovieRepositoryImpl(storage);
        return (T) new MainViewModel(repo);
    }
}
