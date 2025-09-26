package ru.mirea.popov.systemintentsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onOpenWebsiteClick(View view) {
        Uri uri = Uri.parse("https://developer.android.com");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void onDialNumberClick(View view) {
        Uri number = Uri.parse("tel:89811112233");
        Intent intent = new Intent(Intent.ACTION_DIAL, number);
        startActivity(intent);
    }

    public void onOpenMapClick(View view) {
        Uri location = Uri.parse("geo:55.749479,37.613944");
        Intent intent = new Intent(Intent.ACTION_VIEW, location);
        startActivity(intent);
    }
}