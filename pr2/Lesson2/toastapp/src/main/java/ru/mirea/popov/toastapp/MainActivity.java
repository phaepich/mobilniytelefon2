package ru.mirea.popov.toastapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
    }

    public void onShowToastClick(View view) {
        String input = editText.getText().toString();
        int length = input.length();
        Toast.makeText(getApplicationContext(),
                "СТУДЕНТ №22 ГРУППА БСБО-09-22 Количество символов - " + length,
                Toast.LENGTH_LONG).show();
    }
}
