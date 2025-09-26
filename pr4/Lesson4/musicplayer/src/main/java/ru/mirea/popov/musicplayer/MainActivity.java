package ru.mirea.popov.musicplayer;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.popov.musicplayer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.textTrackName.setText("Lo-Fi Beats — Track 22 (номер варианта)");

        binding.buttonPlayPause.setOnClickListener(view ->
                binding.textTrackName.setText("Нажата кнопка ▶"));
    }
}