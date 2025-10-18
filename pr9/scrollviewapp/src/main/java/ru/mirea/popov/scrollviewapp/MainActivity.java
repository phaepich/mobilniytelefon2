package ru.mirea.popov.scrollviewapp;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout wrapper = findViewById(R.id.wrapper);

        long value = 1;
        for (int i = 1; i <= 100; i++) {
            TextView textView = new TextView(this);
            textView.setText("Элемент " + i + ": " + value);
            textView.setTextSize(18);
            textView.setPadding(0, 12, 0, 12);
            wrapper.addView(textView);
            value *= 2;
        }
    }
}
