package ru.mirea.popov.Lesson9.presentation;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.popov.data.repository.MovieRepositoryImpl;
import ru.mirea.popov.domain.repository.MovieRepository;

public class ViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            MovieRepository repo = new MovieRepositoryImpl();
            return (T) new MainViewModel(repo);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
