package ru.mirea.popov.weatherproject.presentation;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.view.MenuItem;

import ru.mirea.popov.weatherproject.R;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNav = findViewById(R.id.bottomNav);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new WeatherFragment(), "weather")
                    .commit();
        }

        bottomNav.setOnItemSelectedListener(this::onBottomSelected);
    }

    private boolean onBottomSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_weather) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new WeatherFragment(), "weather")
                    .addToBackStack("weather")
                    .commit();
            return true;
        } else if (id == R.id.nav_history) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new HistoryFragment(), "history")
                    .addToBackStack("history")
                    .commit();
            return true;
        } else if (id == R.id.nav_profile) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new ProfileFragment(), "profile")
                    .addToBackStack("profile")
                    .commit();
            return true;
        }

        return false;
    }


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
