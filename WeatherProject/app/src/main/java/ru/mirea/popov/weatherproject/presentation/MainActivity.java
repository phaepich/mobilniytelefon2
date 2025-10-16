package ru.mirea.popov.weatherproject.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import ru.mirea.popov.weatherproject.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivity(new Intent(this, AuthActivity.class));
            finish();
            return;
        }
        boolean isGuest = FirebaseAuth.getInstance().getCurrentUser() == null;
        if (isGuest) {
            Toast.makeText(this, "Вы вошли как гость", Toast.LENGTH_SHORT).show();
        }

        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new WeatherFragment())
                    .commit();
        }
    }

}


