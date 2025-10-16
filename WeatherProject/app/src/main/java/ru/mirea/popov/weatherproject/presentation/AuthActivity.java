package ru.mirea.popov.weatherproject.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

import ru.mirea.popov.weatherproject.R;

public class AuthActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        EditText email = findViewById(R.id.emailField);
        EditText pass = findViewById(R.id.passwordField);
        Button signIn = findViewById(R.id.signInButton);
        Button register = findViewById(R.id.registerButton);
        Button guestButton = findViewById(R.id.guestButton);

        mAuth = FirebaseAuth.getInstance();

        signIn.setOnClickListener(v -> {
            mAuth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) startActivity(new Intent(this, MainActivity.class));
                        else Toast.makeText(this, "Ошибка входа", Toast.LENGTH_SHORT).show();
                    });
        });

        register.setOnClickListener(v -> {
            mAuth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) startActivity(new Intent(this, MainActivity.class));
                        else Toast.makeText(this, "Ошибка регистрации", Toast.LENGTH_SHORT).show();
                    });
        });

        guestButton.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

    }
}
