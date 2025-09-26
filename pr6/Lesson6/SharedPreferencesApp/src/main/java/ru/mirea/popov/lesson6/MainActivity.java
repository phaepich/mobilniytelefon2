package ru.mirea.popov.lesson6;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editTextGroup, editTextNumber, editTextFilm;
    private Button buttonSave;
    private static final String PREF_NAME = "mirea_settings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextGroup = findViewById(R.id.editTextGroup);
        editTextNumber = findViewById(R.id.editTextNumber);
        editTextFilm = findViewById(R.id.editTextFilm);
        buttonSave = findViewById(R.id.buttonSave);

        SharedPreferences sharedPref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        editTextGroup.setText(sharedPref.getString("GROUP", ""));
        editTextNumber.setText(String.valueOf(sharedPref.getInt("NUMBER", 0)));
        editTextFilm.setText(sharedPref.getString("FAV_FILM", ""));

        buttonSave.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("GROUP", editTextGroup.getText().toString());
            editor.putInt("NUMBER", Integer.parseInt(editTextNumber.getText().toString()));
            editor.putString("FAV_FILM", editTextFilm.getText().toString());
            editor.apply();
        });
    }
}
